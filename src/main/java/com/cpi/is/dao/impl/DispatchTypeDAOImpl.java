package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.DispatchTypeDAO;
import com.cpi.is.entity.DispatchTypeEntity;
import com.cpi.is.util.HBUtil;

public class DispatchTypeDAOImpl implements DispatchTypeDAO {

	@Override
	public List<DispatchTypeEntity> getDispatchTypes() throws Exception {
		List<DispatchTypeEntity> dispatchTypesX = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			dispatchTypesX = session.createQuery("FROM DispatchTypeEntity T ORDER BY T.dispatchTypeCode ASC", DispatchTypeEntity.class).list();
		}
		return dispatchTypesX;
	}

	@Override
	public String saveItem(DispatchTypeEntity dispatchType) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (dispatchType.getDispatchTypeCode() == null || dispatchType.getDispatchTypeCode().isEmpty()) {
				session.persist(dispatchType); // add a new record
			} else {
				session.merge(dispatchType); // update an existing record
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
	public String deleteItem(DispatchTypeEntity dispatchType) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.remove(session.contains(dispatchType) ? dispatchType : session.merge(dispatchType));
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
