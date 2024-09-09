package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.ProductionMaterialDAO;
import com.cpi.is.entity.ProductionMaterialEntity;
import com.cpi.is.util.HBUtil;

public class ProductionMaterialDAOImpl implements ProductionMaterialDAO {

    @Override
    public List<ProductionMaterialEntity> getProductionMaterial() throws Exception {
        List<ProductionMaterialEntity> productionMaterialList = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            productionMaterialList = session.createQuery("FROM ProductionMaterialEntity ORDER BY pmId ASC", ProductionMaterialEntity.class).list();
        }
        return productionMaterialList;
    }

    @Override
    public String saveItem(ProductionMaterialEntity item) throws Exception {
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (item.getPmId() == null) {
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
    public String deleteItem(ProductionMaterialEntity item) throws Exception {
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

	@Override
	public String saveBulkItems(List<ProductionMaterialEntity> item) throws Exception {
		Transaction transaction = null;
	    try (Session session = HBUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        for (ProductionMaterialEntity entity : item) {
	            if (entity.getPmId() == null) {
	                session.persist(entity); 
	            } else {
	                session.merge(entity); 
	            }
	            
	            if (item.indexOf(entity) % 50 == 0) { 
	                session.flush();
	                session.clear();
	            }
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
}

