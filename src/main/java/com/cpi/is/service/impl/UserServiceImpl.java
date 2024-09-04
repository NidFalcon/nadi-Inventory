package com.cpi.is.service.impl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import com.cpi.is.dao.impl.UserDAOImpl;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAOImpl userDAO;
	private BCryptPasswordEncoder passwordEncoder;
	
	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public UserDAOImpl getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}
	
	private UserEntity jsonToEntity(JSONObject json) {
		return new UserEntity(
				json.getInt("userId"),
				json.getString("username"),
				passwordEncoder.encode(json.getString("password")),
				json.getInt("branchId"),
				"Y"
				);
	}
	
	/*
	@Override
	public UserEntity authenticate(HttpServletRequest request) throws Exception {
		UserEntity user = userDAO.authenticate(request.getParameter("username"));
		if(!(user != null && passwordEncoder.matches(request.getParameter("password"), user.getPassword()))) {
			user = null;
		};
		return user;
	}
	*/
	
	/**
	 * THE OLD AUTHENTICATE
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public UserEntity authenticate(HttpServletRequest request) throws Exception {
		UserEntity user = new UserEntity();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		return userDAO.authenticate(user);
	}

	
	public String registerNewUser(HttpServletRequest request) throws Exception {
		UserEntity user = jsonToEntity(new JSONObject(request.getParameter("user")));
		return userDAO.registerUser(user);
	}

}
