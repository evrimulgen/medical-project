package com.medicalproj.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.medicalproj.common.dao.MedicalCaseMapper;
import com.medicalproj.common.domain.Instance;
import com.medicalproj.common.domain.MedicalCase;
import com.medicalproj.common.domain.MedicalCaseView;
import com.medicalproj.common.domain.Series;
import com.medicalproj.common.domain.Study;
import com.medicalproj.common.exception.ServiceException;
import com.medicalproj.common.service.IFileUploadService;
import com.medicalproj.common.service.IInstanceService;
import com.medicalproj.common.service.IMedicalCaseService;
import com.medicalproj.common.service.IMedicalCaseViewService;
import com.medicalproj.common.service.ISeriesService;
import com.medicalproj.common.service.IStudyService;
import com.medicalproj.common.util.FileUtil;
import com.medicalproj.common.util.FtpUtil;
import com.medicalproj.common.util.UUIDUtil;
import com.medicalproj.web.util.Constants;

import eden.dicomparser.DicomParser;
import eden.dicomparser.data.DicomData;

@Service
public class MedicalCaseServiceImpl implements IMedicalCaseService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MedicalCaseMapper medicalCaseMapper;
	
	@Autowired
	private IStudyService studyService;
	
	@Autowired
	private ISeriesService seriesService;
	
	@Autowired
	private IInstanceService instanceService;
	
	@Autowired
	private IFileUploadService fileUploadServcie;
	
	@Autowired
	private IMedicalCaseViewService medicalCaseViewService;
	
	@Override
	public MedicalCase initNewMedicalCase(Integer creatorUserId) throws ServiceException {
		try {
			MedicalCase medicalCase = new MedicalCase();
			medicalCase.setCreateTime(new Date());
			medicalCase.setCreatorUserId(creatorUserId);
			medicalCase.setIsUploadComplete(Constants.MEDICAL_CASE_IS_UPLOAD_COMPLETE_FALSE);
			medicalCase.setStatus(Constants.MEDICAL_CASE_STATUS_OPEN);
			
			saveOrUpdate(medicalCase);
			return medicalCase;
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	private void saveOrUpdate(MedicalCase domain) {
		if( domain != null ){
			if( domain.getId() == null ){
				medicalCaseMapper.insertSelective(domain);
			}else{
				medicalCaseMapper.updateByPrimaryKeySelective(domain);
			}
		}
		
	}

	@Override
	public void doComplete(Integer medicalCaseId) throws ServiceException {
		try {
			if( medicalCaseId == null ){
				throw new ServiceException("病例ID不可为null");
			}
			
			MedicalCase medicalCase = this.getById(medicalCaseId);
			if( medicalCase == null ){
				throw new ServiceException("病例不存在");
			}
			
			if( medicalCase.getIsUploadComplete() == null ){
				throw new ServiceException("病例完成状态异常");
			}
			
			if( medicalCase.getIsUploadComplete().equals(Constants.MEDICAL_CASE_IS_UPLOAD_COMPLETE_TRUE)){
				throw new ServiceException("病例已经上传完成,不可继续上传");
			}
			
			medicalCase.setIsUploadComplete(Constants.MEDICAL_CASE_IS_UPLOAD_COMPLETE_TRUE);
			this.saveOrUpdate(medicalCase);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
		
		
	}

	private MedicalCase getById(Integer medicalCaseId) {
		return medicalCaseMapper.selectByPrimaryKey(medicalCaseId);
	}

	@Override
	public List<MedicalCaseView> listAllMedicalCaseViewByOwnerId(Integer ownerId)
			throws ServiceException {
		return medicalCaseViewService.listAllByOwnerId(ownerId);
	}

	@Override
	public void addDicomToMedicalCase(Integer medicalCaseId, MultipartFile dicomFile,
			Integer userId) throws ServiceException {
		try {
			if( medicalCaseId == null || dicomFile == null || userId == null ){
				throw new ServiceException("参数错误");
			}
			
			MedicalCase mcase = this.getById(medicalCaseId);
			if( mcase == null ){
				throw new ServiceException("病例不存在");
			}
			
			// upload dicom file to ftp server
			FtpUtil.UploadResult res = FtpUtil.upload(dicomFile.getInputStream(),FileUtil.getSuffix(dicomFile.getOriginalFilename()));
			Integer uploadDicomFileId = null;
			Integer uploadJpgFileId = null;
			if( res == null ){
				throw new ServiceException("上传失败");
			}else{
				// save upload dicom file
				uploadDicomFileId = fileUploadServcie.save(res.getFileName(), res.getRelativePath(), dicomFile.getSize(), Constants.UPLOAD_FILE_TYPE_DICOM, userId);
				
				FileInputStream jpgFileIn = null;
				File jpgFile = null;
				File dcmFile = null;
				try{
					/* convert to jpg and save */
					// convert dcm to jpg
					String tmpdir = System.getProperty("java.io.tmpdir");
					String jpgFilePath = tmpdir + File.separator + UUIDUtil.getUUID() + ".jpg";
					logger.info("创建图片临时文件:" + jpgFilePath);
					String dcmFilePath = tmpdir + File.separator + UUIDUtil.getUUID() + ".jpg";
					dcmFile = new File(dcmFilePath);
					FileUtil.copy(dicomFile.getInputStream(),dcmFile);
					DicomParser.dcm2jpg(dcmFilePath, jpgFilePath);
					
					// upload and save jpg to db
					jpgFile = new File(jpgFilePath);
					jpgFileIn = new FileInputStream(jpgFile);
	
					FtpUtil.UploadResult jpgRes = FtpUtil.upload(jpgFileIn,Constants.FILE_SUFFIX_JPG);
					uploadJpgFileId = fileUploadServcie.save(jpgRes.getFileName(), jpgRes.getRelativePath(), jpgFile.length(), Constants.UPLOAD_FILE_TYPE_DICOM, userId);
				}finally{
					if( jpgFileIn != null ){
						try {
							jpgFileIn.close();
						} catch (Exception e) {
							logger.error(e);
							e.printStackTrace();
						}
						try {
							jpgFile.delete();
							dcmFile.delete();
						} catch (Exception e) {
							logger.error(e);
							e.printStackTrace();
						}
					}
				}
			}
			
			DicomData dicom = DicomParser.getInstance().read(dicomFile.getInputStream());
			// create study
			Study study = studyService.createStydyIfNotExists(medicalCaseId ,dicom);
			
			if( study != null ){
				Integer studyDomainId = study.getId();
				
				Series series = seriesService.createSeriesIfNotExists(studyDomainId,dicom);
				
				if( series != null ){
					Integer seriesDomainId = series.getId();
					
					Instance instance = instanceService.createInstanceIfNotExists(seriesDomainId, dicom, uploadDicomFileId,uploadJpgFileId);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public MedicalCaseView getMedicalCaseViewById(Integer medicalCaseId)
			throws ServiceException {
		return medicalCaseViewService.getById(medicalCaseId);
	}
	
}
