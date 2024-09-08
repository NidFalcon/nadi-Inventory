package com.cpi.is.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.cpi.is.dao.FinishedProductListDAO;
import com.cpi.is.entity.FinishedProductListEntity;
import com.cpi.is.util.HBUtil;

public class FinishedProductListDAOImpl implements FinishedProductListDAO {

    @Override
    public List<FinishedProductListEntity> getFinishedProductList() throws Exception { // Fixed method name
        List<FinishedProductListEntity> finishedProductList = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            finishedProductList = session.createQuery("FROM FinishedProductListEntity T ORDER BY T.fplId ASC", FinishedProductListEntity.class).list();
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