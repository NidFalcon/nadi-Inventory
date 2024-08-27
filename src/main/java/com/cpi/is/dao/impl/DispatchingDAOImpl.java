package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.DispatchingDAO;
import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.util.HBUtil;

public class DispatchingDAOImpl implements DispatchingDAO {
	
	@Override
	public List<DispatchingEntity> getDispatching() throws Exception {
		List<DispatchingEntity> dispatching = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			dispatching = (List<DispatchingEntity>) 
					session.createQuery("From DispatchingEntity T ORDER BY T.dispatchTrackId ASC", DispatchingEntity.class).list();
		}
		return dispatching;
	}

	@Override
	public String saveItem(DispatchingEntity item) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (0 == item.getFplId()) {
				session.persist(item);	// add a new record
			} else {
				session.merge(item);	// update an existing record
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
		return "success";
	}

	@Override
	public String deleteItem(DispatchingEntity item) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.remove(item);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
		return "success";
	}
	
}
