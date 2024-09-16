package com.cpi.is.dao.impl.dpp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.dpp.DppDAO;
import com.cpi.is.entity.dpp.DppEntity;
import com.cpi.is.util.HBUtil;

public class DppDAOImpl implements DppDAO {

    @Override
    public List<DppEntity> getDpp(Long branchId) throws Exception {
        List<DppEntity> dppList = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            dppList = session.createQuery("FROM DppEntity WHERE branchId = :branchId ORDER BY dppId", 
            		DppEntity.class).setParameter("branchId", branchId ).list();
        }
        return dppList;
    }
    
    @Override
    public DppEntity getDppById(Long dppId) throws Exception {
    	DppEntity dpp = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
        	dpp = session.get(DppEntity.class, dppId); 
        } catch (Exception e) {
            throw e;
        }
        return dpp;
    }

    @Override
    public String saveItem(DppEntity item) throws Exception {
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (item.getDppId() == null) {
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
    public String deleteItem(DppEntity item) throws Exception {
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
