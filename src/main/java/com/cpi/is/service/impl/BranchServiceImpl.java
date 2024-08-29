package com.cpi.is.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.cpi.is.dao.impl.BranchDAOImpl;
import com.cpi.is.dao.impl.InventoryDAOImpl;
import com.cpi.is.entity.BranchEntity;
import com.cpi.is.entity.InventoryEntity;
import com.cpi.is.util.HBUtil;

public class BranchServiceImpl {
	
	private BranchDAOImpl branchDAO;
	
	public BranchDAOImpl getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAOImpl branchDAO) {
		this.branchDAO = branchDAO;
	}

	public List<BranchEntity> getAllBranches() throws Exception {
		return branchDAO.getAllBranches();
	}
}
