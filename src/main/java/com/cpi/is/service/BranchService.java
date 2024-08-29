package com.cpi.is.service;

import java.util.List;

import com.cpi.is.dao.impl.BranchDAOImpl;
import com.cpi.is.entity.BranchEntity;

public interface BranchService {
	
	public BranchDAOImpl getBranchDAO();
	public void setBranchDAO(BranchDAOImpl branchDAO);
	public List<BranchEntity> getAllBranches() throws Exception;
}
