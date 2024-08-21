package com.cpi.is.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Map;

import com.cpi.is.entity.UserEntity;

public interface UserDAO {

	UserEntity authenticate(Map<String, Object> params) throws FileNotFoundException, ClassNotFoundException, SQLException;
	
}
