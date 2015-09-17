package com.medicalproj.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalproj.common.dto.view.View;
import com.medicalproj.common.exception.ServiceException;
import com.medicalproj.common.service.IQualificationApplicationService;
import com.medicalproj.web.service.IWebQualificationApplyService;

@Service
public class WebQualificationApplyServiceImpl implements
		IWebQualificationApplyService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private IQualificationApplicationService qualificationApplicationService;
	
	@Override
	public View<Boolean> submitApplication(Integer yszgzId,
			Integer auditUserId, Integer applyUserId) throws ServiceException {
		View<Boolean> view = new View<Boolean>();
		try {
			qualificationApplicationService.submitApplication(yszgzId,auditUserId,applyUserId);
			view.setData(true);
			return view;
		} catch (Exception e) {
			logger.error(e);
			view.setData(false);
			view.setMsg(e.getMessage());
			return view;
		}
	}

}
