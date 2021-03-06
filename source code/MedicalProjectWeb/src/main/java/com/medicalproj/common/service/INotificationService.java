package com.medicalproj.common.service;

import java.util.List;

import com.medicalproj.common.domain.Notification;
import com.medicalproj.common.domain.NotificationView;
import com.medicalproj.common.exception.ServiceException;

public interface INotificationService {

	Integer getUnreadNotificationCountByUser(Integer userId)throws ServiceException;

	List<NotificationView> listNotificationByUser(Integer userId, Integer page, Integer pageSize)throws ServiceException;

	Integer countNotificationByUser(Integer userId)throws ServiceException;

	void setNotificationRead(Integer notificationId)throws ServiceException;

	Notification getById(Integer notificationId)throws ServiceException;

	void createDiagnoseInviteNotification(Integer processUserId, Integer studyId, Integer toUserId)throws ServiceException;

	void approve(Integer notificationId, Integer processUserId)throws ServiceException;

	void reject(Integer notificationId, Integer processUserId)throws ServiceException;

	void doExpireNotificationByPeriod(int notificationConfirmExpirePeriod)throws ServiceException;
	
	List<Notification> listAllByStatus(int status)throws ServiceException;
}
