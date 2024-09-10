package com.cpi.is.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.UserDAO;
import com.cpi.is.entity.BranchEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.util.HBUtil;

//public class UserDAOImpl implements UserDAO {
public class UserDAOImpl{

	//password ni Sunday: R0b1N1234
	public UserEntity authenticate(String username) throws Exception {
		UserEntity authenticated = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			List<UserEntity> results = (List<UserEntity>) session
					.createQuery("FROM UserEntity U WHERE U.username = :username", UserEntity.class)
					.setParameter("username", username)
					.list();
			if (results.size() > 0) {
				authenticated = results.get(0);
			}
		}
		return authenticated;
	}
	
	
	/**
	 * The Old User Authentication Logic
	 * Replace in a later version.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	/*
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
	*/
	
	//@Override
	public String registerUser(UserEntity user) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (0 == user.getUserId()) {
				user.setUserId(null);; 
				session.persist(user);	// add a new record
			} else {
				session.merge(user);	// update an existing record
			}
			System.out.println("Nevermind");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
		return "success";
	}
	
	public UserEntity getUser(Integer userId) throws Exception {
		UserEntity foundUser = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			List<UserEntity> results = (List<UserEntity>) session
					.createQuery("FROM UserEntity U WHERE U.userId = :userId", UserEntity.class)
					.setParameter("userId", userId)
					.list();
			if (results.size() > 0) {
				foundUser = results.get(0);
			}
			
			return foundUser;
		}
	}

}

