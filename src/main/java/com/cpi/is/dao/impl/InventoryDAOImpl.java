package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.InventoryDAO;
import com.cpi.is.entity.InventoryEntity;
import com.cpi.is.util.HBUtil;

public class InventoryDAOImpl implements InventoryDAO {
	
	@Override
	public List<InventoryEntity> getInventory() throws Exception {
		List<InventoryEntity> inventory = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			inventory = (List<InventoryEntity>) 
					session.createQuery("From InventoryEntity T ORDER BY T.inventoryId ASC", InventoryEntity.class).list();
		}
		return inventory;
	}

	@Override
	public String saveItem(InventoryEntity item) throws Exception {
		Transaction transaction = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (0 == item.getInventoryId()) {
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
	public String deleteItem(InventoryEntity item) throws Exception {
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
