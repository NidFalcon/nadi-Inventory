package com.cpi.is.dao.impl.inventory;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.inventory.RawMaterialListDAO;
import com.cpi.is.entity.inventory.RawMaterialListEntity;
import com.cpi.is.util.HBUtil;

public class RawMaterialListDAOImpl implements RawMaterialListDAO {

	public List<RawMaterialListEntity>  getRawMaterialList(Long targetBranchId) throws Exception {
        try (Session session = HBUtil.getSessionFactory().openSession()){
        	List<RawMaterialListEntity> rawMaterialLists = session.createQuery("FROM RawMaterialListEntity R WHERE R.branchId = :targetBranchId ORDER BY R.materialListId ASC",RawMaterialListEntity.class)
        			    .setParameter("targetBranchId", targetBranchId)
        			    .list();
        	return rawMaterialLists;
        }
	}
	
	public RawMaterialListEntity getRawMaterialListById(Long primaryKey) throws Exception {
		RawMaterialListEntity rawMaterialList = null;
		try (Session session = HBUtil.getSessionFactory().openSession()){
			rawMaterialList = session.get(RawMaterialListEntity.class, primaryKey); 
		}
		return rawMaterialList;
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

}
