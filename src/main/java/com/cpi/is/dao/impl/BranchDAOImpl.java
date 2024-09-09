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
    public BranchEntity getBranchById(Long branchId) throws Exception {
        BranchEntity branch = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            branch = session.get(BranchEntity.class, branchId);
        } catch (Exception e) {
            throw e;
        }
        return branch;
    }

	public BranchEntity getBranch(Integer branchId) {
		BranchEntity foundBranch = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			List<BranchEntity> results = (List<BranchEntity>) session
					.createQuery("FROM BranchEntity B WHERE B.branchId = :branchId", BranchEntity.class)
					.setParameter("branchId", branchId)
					.list();
			if (results.size() > 0) {
				foundBranch = results.get(0);
			}
			
			return foundBranch;
		}
	}

    @Override
    public String saveItem(BranchEntity item) throws Exception {
        Transaction transaction = null;
        try (Session session = HBUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (item.getBranchId() == null) {
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
    public String deleteItem(BranchEntity item) throws Exception {
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
