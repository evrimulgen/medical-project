package com.medicalproj.common.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.medicalproj.common.dto.view.FileView;
import com.medicalproj.common.dto.view.View;
import com.medicalproj.common.exception.ServiceException;
import com.medicalproj.common.service.IFileUploadService;
import com.medicalproj.common.service.IUploadService;
import com.medicalproj.common.util.DateUtil;
import com.medicalproj.common.util.FileUtil;
import com.medicalproj.common.util.FtpUtil;
import com.medicalproj.common.util.UUIDUtil;

@Service
public class UploadServiceImpl implements IUploadService {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IFileUploadService fileUploadService;
	
	@Override
	public View<FileView> upload(MultipartFile file,Integer uploadUserId) throws ServiceException {
		View<FileView> view = new View<FileView>();
		try {
			if (file == null) {
				throw new ServiceException("文件上传出错");
			}

			Date now = new Date();
			int year = DateUtil.getYear(now);
			int month = DateUtil.getMonth(now);
			String suffix = FileUtil.getSuffix(file.getOriginalFilename());
			long fileSize = file.getSize();
			
			
			if( !FileUtil.isFileSupport(file.getContentType()) ){
				throw new ServiceException("文件格式不支持.");
			}
			
			String fileName = UUIDUtil.getUUID() + "." + suffix;
			
			try {
				FtpUtil.upload(file.getInputStream(), year + "/" + month,
						fileName);
			} catch (Exception e) {
				throw new ServiceException("上传文件失败");
			}

			// 保存文件
			String imageRelativePath = year + "/" + month + "/" + fileName;
			int fileType = FileUtil.getType(file.getContentType());
			Integer imageId = fileUploadService.save(fileName,imageRelativePath,fileSize,fileType,uploadUserId);

			FileView fileView = new FileView();
			fileView.setPath(FtpUtil.getAbsPath(imageRelativePath));
			fileView.setId(imageId);
			
			view.setData(fileView);
			return view;
		} catch (ServiceException e) {
			view.setData(null);
			view.setMsg(e.getMessage());
			return view;
		}
	}

}
