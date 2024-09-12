package com.cpi.is.dao.impl.maintenance;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.maintenance.RawMaterialDAO;
import com.cpi.is.entity.maintenance.RawMaterialEntity;
import com.cpi.is.util.HBUtil;

public class RawMaterialDAOImpl implements RawMaterialDAO {

    @Override
    public List<RawMaterialEntity> getRawMaterial() throws Exception {
        List<RawMaterialEntity> rawMaterialList = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            rawMaterialList = session.createQuery("FROM RawMaterialEntity R ORDER BY R.materialCode ASC", RawMaterialEntity.class).list();
        }
        return rawMaterialList;
    }
    
    @Override
    public RawMaterialEntity getRawMaterialById(String materialCode) throws Exception {
        RawMaterialEntity rawMaterial = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            rawMaterial = session.get(RawMaterialEntity.class, materialCode); 
        } catch (Exception e) {
            throw e;
        }
        return rawMaterial;
    }

    @Override
    public String saveItem(RawMaterialEntity item) throws Exception {
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (item.getMaterialCode() == null || item.getMaterialCode().isEmpty()) {
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
    public String deleteItem(RawMaterialEntity item) throws Exception {
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
