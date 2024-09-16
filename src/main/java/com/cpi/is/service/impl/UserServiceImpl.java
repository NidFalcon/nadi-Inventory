  package com.cpi.is.service.impl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.json.JSONObject;

import com.cpi.is.dao.impl.UserDAOImpl;
import com.cpi.is.entity.SessionEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.UserService;
import com.cpi.is.util.CookieUtil;

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
				json.getLong("userId"),
				json.getString("username"),
				passwordEncoder.encode(json.getString("password")),
				json.getLong("branchId"),
				"Y"
				);
	}
	
	
	@Override
	public UserEntity authenticate(HttpServletRequest request) throws Exception {
		UserEntity user = userDAO.authenticate(request.getParameter("username"));
		if(!(user != null && passwordEncoder.matches(request.getParameter("password"), user.getPassword()))) {
			user = null;
		} else {
			user.setPassword("");
		};
		return user;
	}
	
	public void saveSession(HttpServletRequest request) throws Exception {
		userDAO.saveSession(new SessionEntity(
				request.getSession().getId(), request.getAttribute("username").toString()));
	}


	public SessionEntity validateSession(HttpServletRequest request) throws Exception {
		return userDAO.validateSession(new SessionEntity(
				CookieUtil.getCookieValue(request.getCookies(), "sessionId"), 
				CookieUtil.getCookieValue(request.getCookies(), "user")));
	}


	public void deleteSession(HttpServletRequest request) throws Exception {
		userDAO.deleteSession(new SessionEntity(
				CookieUtil.getCookieValue(request.getCookies(), "sessionId"), 
				CookieUtil.getCookieValue(request.getCookies(), "user")));
	}
	
	public String registerNewUser(HttpServletRequest request) throws Exception {
		UserEntity user = jsonToEntity(new JSONObject(request.getParameter("user")));
		return userDAO.registerUser(user);
	}

}
