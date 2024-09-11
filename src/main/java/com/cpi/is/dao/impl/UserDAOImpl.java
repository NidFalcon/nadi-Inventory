package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.entity.SessionEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.util.HBUtil;

public class UserDAOImpl{

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

	public void saveSession(SessionEntity userSession) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(userSession);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	public SessionEntity validateSession(SessionEntity userSession) throws Exception {
		SessionEntity validated = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			List<SessionEntity> results = session
					.createQuery("FROM SessionEntity T WHERE T.sessionId = :sessionId AND T.username = :username", SessionEntity.class)
					.setParameter("sessionId", userSession.getSessionId())
					.setParameter("username", userSession.getUsername())
					.list();
			if (results.size() > 0) {
				validated = results.get(0);
			}
		}
		return validated;
	}

	public void deleteSession(SessionEntity userSession) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.remove(userSession);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}
}



