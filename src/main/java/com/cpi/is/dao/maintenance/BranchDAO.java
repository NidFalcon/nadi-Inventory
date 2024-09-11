package com.cpi.is.dao.maintenance;

import java.util.List;

import com.cpi.is.entity.maintenance.BranchEntity;

public interface BranchDAO {
	List<BranchEntity> getBranch() throws Exception;
	BranchEntity getBranchById(Long branchId) throws Exception;
	String saveItem(BranchEntity item) throws Exception;
	String deleteItem(BranchEntity item) throws Exception;
}
