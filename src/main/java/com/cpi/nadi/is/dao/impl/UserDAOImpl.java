package com.cpi.nadi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.cpi.nadi.is.entity.User;
import com.cpi.nadi.is.util.HBUtil;

public class UserDAOImpl {
	
	public User authenticate(User user) throws Exception {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authenticated;
	}
}
