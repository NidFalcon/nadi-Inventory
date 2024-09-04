package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.BranchEntity;

public interface BranchDAO {
	List<BranchEntity> getBranch() throws Exception;
	String saveItem(BranchEntity item) throws Exception;
	String deleteItem(BranchEntity item) throws Exception;
}
