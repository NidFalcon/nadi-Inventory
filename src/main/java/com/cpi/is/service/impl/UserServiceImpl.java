package com.cpi.is.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.dao.impl.UserDAOImpl;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAOImpl userDAO;
	
	public UserDAOImpl getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public UserEntity authenticate(HttpServletRequest request) throws Exception {
		UserEntity user = new UserEntity();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		return userDAO.authenticate(user);
	}

}
