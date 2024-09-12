package com.cpi.is.dao.impl.inventory;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.inventory.FinishedProductListDAO;
import com.cpi.is.entity.inventory.FinishedProductListEntity;
import com.cpi.is.util.HBUtil;

public class FinishedProductListDAOImpl implements FinishedProductListDAO {

    @Override
    public List<FinishedProductListEntity> getFinishedProductList(Integer branchId) throws Exception { // Fixed method name
        List<FinishedProductListEntity> finishedProductList = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            finishedProductList = session.createQuery("FROM FinishedProductListEntity WHERE branchId = :branchId ORDER BY fplId ASC", 
            		FinishedProductListEntity.class).setParameter("branchId", branchId ).list();
        }
        return finishedProductList;
    }

    @Override
    public String saveItem(FinishedProductListEntity item) throws Exception { // Fixed parameter type
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (item.getFplId() == null) {
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
    public String deleteItem(FinishedProductListEntity item) throws Exception { // Fixed parameter type
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