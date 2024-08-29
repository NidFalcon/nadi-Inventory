package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.BranchEntity;

public interface BranchDAO {
	
	List<BranchEntity> getBranch() throws Exception;
	String saveItem(BranchEntity dispatchType) throws Exception;
	String deleteItem(BranchEntity dispatchType) throws Exception;
	
}
