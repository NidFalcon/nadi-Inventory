package com.cpi.is.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

import com.cpi.is.dao.DispatchingDAO;
import com.cpi.is.entity.DispatchTypeEntity;
import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.util.HBUtil;

public class DispatchingDAOImpl implements DispatchingDAO {

    @Override
    public List<DispatchingEntity> getDispatchingByBranchId(Integer branchId) throws Exception {
        List<DispatchingEntity> dispatching = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            dispatching = session.createQuery("SELECT T FROM DispatchingEntity T JOIN T.branch J WHERE T.branchId = :branchId AND J.branchId = T.branchId ORDER BY T.dispatchTrackId ASC", DispatchingEntity.class)
                                 .setParameter("branchId", branchId)
                                 .list();
        }
        return dispatching;
    }
    
    @Override
    public String saveItem(DispatchingEntity item) throws Exception {
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (0 == item.getDispatchTrackId()) {
            	item.setDispatchTrackId(null);
                session.persist(item);    // add a new record
            } else {
                session.merge(item);    // update an existing record
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
