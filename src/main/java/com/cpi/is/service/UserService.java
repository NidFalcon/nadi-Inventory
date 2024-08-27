package com.cpi.is.service;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.UserEntity;

public interface UserService {

	UserEntity authenticate(HttpServletRequest request) throws Exception;
	
}
