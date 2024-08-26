package com.cpi.nadi.is.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.cpi.nadi.is.entity.User;

public interface UserService {
	public User authenticate(HttpServletRequest request) throws SQLException;
}
