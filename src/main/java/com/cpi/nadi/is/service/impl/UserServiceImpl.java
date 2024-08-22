package com.cpi.nadi.is.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.cpi.nadi.is.dao.impl.UserDAOImpl;
import com.cpi.nadi.is.entity.User;

public class UserServiceImpl {
	
	private UserDAOImpl userDAO = new UserDAOImpl();
	
	public User authenticate(HttpServletRequest request) throws Exception {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		return userDAO.authenticate(user);
	}
}
