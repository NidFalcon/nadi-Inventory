package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.cpi.is.dao.UserDAO;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.util.HBUtil;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public String registerUser(UserEntity user) throws Exception {
		return "YAY!";
	}

	@Override
	public UserEntity authenticate(UserEntity user) throws Exception {
		UserEntity authenticated = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			List<UserEntity> results = (List<UserEntity>) session
					.createQuery("FROM UserEntity U WHERE U.username = :username AND U.password = :password", UserEntity.class)
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
