package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpi.is.dao.BranchDAO;
import com.cpi.is.entity.BranchEntity;
import com.cpi.is.util.HBUtil;

public class BranchDAOImpl implements BranchDAO {

    @Override
    public List<BranchEntity> getBranch() throws Exception {
        List<BranchEntity> branchList = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            branchList = session.createQuery("FROM BranchEntity T ORDER BY T.branchId ASC", BranchEntity.class).list();
        }
        return branchList;
    }

    @Override
    public String saveItem(BranchEntity branch) throws Exception {
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (branch.getBranchId() == null) {
                session.persist(branch);
            } else {
                session.merge(branch);
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
    public String deleteItem(BranchEntity branch) throws Exception {
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.contains(branch) ? branch : session.merge(branch));
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
