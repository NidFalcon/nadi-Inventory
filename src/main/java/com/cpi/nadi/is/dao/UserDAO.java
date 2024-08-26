package com.cpi.nadi.is.dao;

import java.sql.SQLException;

import com.cpi.nadi.is.entity.User;

public interface UserDAO {
	public User authenticate(User user) throws SQLException;
}
