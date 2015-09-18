package com.medicalproj.common.service;

import java.util.List;

import com.medicalproj.common.domain.User;
import com.medicalproj.common.domain.UserView;
import com.medicalproj.common.exception.ServiceException;

public interface IUserService {

	User getByMobile(String mobile)throws ServiceException;

	User getByEmail(String email)throws ServiceException;

	User initUserByMobile(String mobile)throws ServiceException;

	void saveOrUpdate(User user)throws ServiceException;

	User getByMobileOrEmail(String account)throws ServiceException;

	User getById(Integer userId)throws ServiceException;

	List<UserView> listAllSeniorDoctor()throws ServiceException;

}
