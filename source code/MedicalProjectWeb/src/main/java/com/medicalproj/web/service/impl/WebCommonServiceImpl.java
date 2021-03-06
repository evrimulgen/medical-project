package com.medicalproj.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalproj.common.domain.UserView;
import com.medicalproj.common.dto.view.View;
import com.medicalproj.common.exception.ServiceException;
import com.medicalproj.common.service.IAuthService;
import com.medicalproj.common.service.INotificationService;
import com.medicalproj.common.service.IUserService;
import com.medicalproj.web.service.IWebCommonService;

@Service
public class WebCommonServiceImpl implements IWebCommonService {
	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	private IAuthService authService; 
	
	@Autowired
	private IUserService userService;
	
	@Override
	public View<Boolean> getMobileVerifyCode(String mobile) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View<Integer> getUnreadNotificaitonCount(Integer userId) throws ServiceException {
		View<Integer> view = new View<Integer>();
		try {
			Integer unreadNotificationCount = notificationService.getUnreadNotificationCountByUser(userId);
			view.setData(unreadNotificationCount);
			return view;
		} catch (Exception e) {
			view.setMsg(e.getMessage());
			return view;
		}
	}

	@Override
	public View<Boolean> logout() throws ServiceException {
		View<Boolean> view = new View<Boolean>();
		try {
			authService.logout();
			view.setData(true);
			
			return view;
		} catch (Exception e) {
			view.setData(false);
			view.setMsg(e.getMessage());
			return view;
		}
	}

	@Override
	public View<List<UserView>> listAllJuniorDoctor() throws ServiceException {
		View<List<UserView>> view = new View<List<UserView>>();
		List<UserView> userList = userService.listAllJuniorDoctor();
		view.setData(userList);
		return view;
	}

	@Override
	public View<UserView> getLoginUserInfo(Integer userId) throws ServiceException {
		View<UserView> view = new View<UserView>();
		UserView userView = userService.getUserViewByUserId(userId);
		
		view.setData(userView);
		return view;
	}

	
}
