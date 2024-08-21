package com.cpi.is.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.UserEntity;

public interface UserService {

	UserEntity authenticate(HttpServletRequest request) throws FileNotFoundException, ClassNotFoundException, SQLException;
	
}
