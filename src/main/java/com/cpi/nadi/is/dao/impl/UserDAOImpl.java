package com.cpi.nadi.is.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.cpi.nadi.is.dao.UserDAO;
import com.cpi.nadi.is.entity.User;
import com.cpi.nadi.is.util.HBUtil;

public class UserDAOImpl implements UserDAO{
	
	public User authenticate(User user) throws SQLException {
		User authenticated = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			List<User> results = (List<User>) session
					.createQuery("FROM User U WHERE U.username = :username AND U.password = :password", User.class)
					.setParameter("username", user.getUsername())
					.setParameter("password", user.getPassword())
					.list();
			if (results.size() > 0) {
				authenticated = results.get(0);  
			}
		}
		return authenticated;
	}
}
