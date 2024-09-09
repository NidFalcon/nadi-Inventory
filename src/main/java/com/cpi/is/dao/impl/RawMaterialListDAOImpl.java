package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.cpi.is.dao.RawMaterialListDAO;
import com.cpi.is.entity.RawMaterialListEntity;
import com.cpi.is.util.HBUtil;
import com.cpi.is.validation.ForeignKeyValidate;

public class RawMaterialListDAOImpl implements RawMaterialListDAO, ForeignKeyValidate {

	public List<RawMaterialListEntity>  getRawMaterialList(Integer targetBranchId) throws Exception {
        try (Session session = HBUtil.getSessionFactory().openSession()){
        	List<RawMaterialListEntity> rawMaterialLists = session.createQuery("FROM RawMaterialListEntity R WHERE R.branchId = :targetBranchId"
        				, RawMaterialListEntity.class)
        			    .setParameter("targetBranchId", targetBranchId)
        			    .list();
        	return rawMaterialLists;
        }
	}

	@Override
	public String saveRawMaterial(RawMaterialListEntity item) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (0 == item.getMaterialListId()) {
				item.setMaterialListId(null); 
				session.persist(item);	// add a new record
			} else {
				session.merge(item);	// update an existing record
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

	@Override
	public String deleteRawMaterial(RawMaterialListEntity item) throws Exception {
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

	@Override
	public boolean isValidForeignKey(Session session, String tableName, String columnName, String value) {
	    Transaction tx = null;
	    boolean isValid = false;
	    try {
	        tx = session.beginTransaction();
	        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = :value";
	        Query<Long> query = session.createNativeQuery(sql, Long.class);
	        query.setParameter("value", value);
	        Long count = query.uniqueResult();
	        tx.commit();
	        isValid = count != null && count > 0;
	    } catch (HibernateException e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw e; // Rethrow the exception to the caller
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close(); // Ensure session is closed
	        }
	    }
	    return isValid;
	}
}
