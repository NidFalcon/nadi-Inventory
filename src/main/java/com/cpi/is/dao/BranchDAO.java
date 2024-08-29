package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.BranchEntity;


public interface BranchDAO {
	public List<BranchEntity>getAllBranches() throws Exception;
}
