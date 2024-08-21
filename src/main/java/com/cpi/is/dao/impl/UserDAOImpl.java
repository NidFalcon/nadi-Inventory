package com.cpi.is.dao.impl;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.cpi.is.dao.UserDAO;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.util.DBUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public UserEntity authenticate(Map<String, Object> params) throws FileNotFoundException, ClassNotFoundException, SQLException {
		UserEntity user = null;
		
		String query = "SELECT user_id, username, password FROM inv_users WHERE username = ? AND password = ?";
		PreparedStatement stmt = (PreparedStatement) DBUtil.getStmt(query, false);
		
		String username = (String) params.get("username");
		String password = (String) params.get("password");
		String[] queryParams = {username, password};
		
		try (ResultSet result = DBUtil.select(stmt, queryParams)) {
			while(result.next()) {
				user = new UserEntity(result.getInt("USER_ID"), 
						result.getString("USERNAME"), result.getString("PASSWORD"));
			}
		}
		
		return user;
	}

}
