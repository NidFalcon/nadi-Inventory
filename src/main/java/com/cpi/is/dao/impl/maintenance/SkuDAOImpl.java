package com.cpi.is.dao.impl.maintenance;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.maintenance.SkuDAO;
import com.cpi.is.entity.maintenance.SkuEntity;
import com.cpi.is.util.HBUtil;

public class SkuDAOImpl implements SkuDAO {

    @Override
    public List<SkuEntity> getSku() throws Exception {
        List<SkuEntity> skuList = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            skuList = session.createQuery("FROM SkuEntity S ORDER BY S.skuCode ASC", SkuEntity.class).list();
        }
        return skuList;
    }
    
    @Override
    public SkuEntity getSkuById(String skuCode) throws Exception {
        SkuEntity sku = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            sku = session.get(SkuEntity.class, skuCode); 
        } catch (Exception e) {
            throw e;
        }
        return sku;
    }

    @Override
    public String saveItem(SkuEntity item) throws Exception {
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (item.getSkuCode() == null || item.getSkuCode().isEmpty()) {
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
    public String deleteItem(SkuEntity item) throws Exception {
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
