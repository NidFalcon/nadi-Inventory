package com.cpi.is.dao.impl.dpp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.dpp.ProductionMaterialDAO;
import com.cpi.is.entity.dpp.ProductionMaterialEntity;
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
    public ProductionMaterialEntity getProductionMaterialById(Long pmId) throws Exception {
    	ProductionMaterialEntity pm = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
        	pm = session.get(ProductionMaterialEntity.class, pmId); 
        } catch (Exception e) {
            throw e;
        }
        return pm;
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

