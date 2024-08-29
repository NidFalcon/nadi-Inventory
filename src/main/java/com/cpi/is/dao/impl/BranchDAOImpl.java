package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.cpi.is.dao.BranchDAO;
import com.cpi.is.entity.BranchEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.util.HBUtil;

public class BranchDAOImpl implements BranchDAO {
	
	@Override
	public List<BranchEntity> getAllBranches() throws Exception {
		List<BranchEntity> results;
		try(Session session = HBUtil.getSessionFactory().openSession()) {
			results = (List<BranchEntity>) session
					.createQuery("FROM BranchEntity B", BranchEntity.class)
					.list();
			
			if (results.isEmpty()) {
				throw new Exception("Temporary Exception: No Results");
			}
			
			return results;
		} 
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

}
