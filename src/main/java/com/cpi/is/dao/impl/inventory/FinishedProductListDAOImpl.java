package com.cpi.is.dao.impl.inventory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.inventory.FinishedProductListDAO;
import com.cpi.is.entity.inventory.FinishedProductListEntity;
import com.cpi.is.util.HBUtil;

public class FinishedProductListDAOImpl implements FinishedProductListDAO{

	@Override
	public List<FinishedProductListEntity> getFinishedProductList(Integer targetBranchId) throws Exception {
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			List<FinishedProductListEntity> finishedProductList = session.createQuery("FROM FinishedProductListEntity F WHERE F.branchId = :targetBranchId ORDER BY F.fplId ASC"
					,FinishedProductListEntity.class)
					.setParameter("targetBranchId",targetBranchId)
					.list();
			return finishedProductList;
		}
	}

	@Override
	public String saveProduct(FinishedProductListEntity item) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (0 == item.getFplId()) {
				item.setFplId(null); 
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
	public String deleteProduct(FinishedProductListEntity item) throws Exception {
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
