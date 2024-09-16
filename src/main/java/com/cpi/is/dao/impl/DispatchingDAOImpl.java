package com.cpi.is.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

import com.cpi.is.dao.DispatchingDAO;
import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.util.HBUtil;

public class DispatchingDAOImpl implements DispatchingDAO {

	@Override
	public List<DispatchingEntity> getDispatchingByBranchId(Long branchId) throws Exception {
		List<DispatchingEntity> dispatching = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			dispatching = session.createQuery(
					"SELECT T FROM DispatchingEntity T JOIN T.branch J WHERE T.branchId = :branchId AND J.branchId = T.branchId ORDER BY T.dispatchTrackId ASC",
					DispatchingEntity.class).setParameter("branchId", branchId).list();
		}
		return dispatching;
	}

	public List<Object[]> getCurrentInventory(Long branchId) throws Exception {
	    List<Object[]> result = null;
	    try (Session session = HBUtil.getSessionFactory().openSession()) {
	        result = session.createQuery(
	            "SELECT fpl.skuCD, " +
	            "(COALESCE(MAX(fpl.quantity), 0) - COALESCE(SUM(dispatch.quantity), 0)) AS availableQuantity, " +
	            "fpl.fplId " +
	            "FROM FinishedProductListEntity fpl " +
	            "LEFT JOIN DispatchingEntity dispatch ON fpl.fplId = dispatch.fplId " +
	            "WHERE fpl.branchId = :branchId " +
	            "GROUP BY fpl.fplId, fpl.skuCD " +
	            "ORDER BY fpl.fplId",
	            Object[].class
	        ).setParameter("branchId", branchId).list();
	    }
	    return result;
	}

	@Override
	public String saveItem(DispatchingEntity item) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (0 == item.getDispatchTrackId()) {
				item.setDispatchTrackId(null);
				session.persist(item); // add a new record
			} else {
				session.merge(item); // update an existing record
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
