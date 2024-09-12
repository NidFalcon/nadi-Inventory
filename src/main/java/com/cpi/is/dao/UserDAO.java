package com.cpi.is.dao;

import com.cpi.is.entity.UserEntity;

public interface UserDAO {
	UserEntity authenticate(String username) throws Exception;
	String registerUser(UserEntity user) throws Exception;
}