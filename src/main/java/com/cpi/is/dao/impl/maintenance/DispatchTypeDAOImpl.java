package com.cpi.is.dao.impl.maintenance;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.maintenance.DispatchTypeDAO;
import com.cpi.is.entity.maintenance.DispatchTypeEntity;
import com.cpi.is.util.HBUtil;

public class DispatchTypeDAOImpl implements DispatchTypeDAO {

	@Override
	public List<DispatchTypeEntity> getDispatchType() throws Exception {
		List<DispatchTypeEntity> dispatchType = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			dispatchType = session.createQuery("FROM DispatchTypeEntity T ORDER BY T.dispatchTypeCode ASC", DispatchTypeEntity.class).list();
		}
		return dispatchType;
	}
	
	@Override
	public DispatchTypeEntity getDispatchTypeById(String dispatchTypeCode) throws Exception {
	    DispatchTypeEntity dispatchType = null;
	    try (Session session = HBUtil.getSessionFactory().openSession()) {
	        dispatchType = session.get(DispatchTypeEntity.class, dispatchTypeCode); 
	    } catch (Exception e) {
	        throw e;
	    }
	    return dispatchType;
	}
	
	@Override
	public String saveItem(DispatchTypeEntity item) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (item.getDispatchTypeCode() == null || item.getDispatchTypeCode().isEmpty()) {
				session.persist(item);
			} else {
				session.merge(item);
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
	public String deleteItem(DispatchTypeEntity item) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.remove(session.contains(item) ? item : session.merge(item));
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
