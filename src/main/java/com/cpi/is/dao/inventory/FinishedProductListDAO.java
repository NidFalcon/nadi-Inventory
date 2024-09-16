package com.cpi.is.dao.inventory;

import java.util.List;

import com.cpi.is.entity.inventory.FinishedProductListEntity;

public interface FinishedProductListDAO {
	
	List<FinishedProductListEntity> getFinishedProductList(Long targetBranchId) throws Exception;
	String saveProduct(FinishedProductListEntity item) throws Exception;
	String deleteProduct(FinishedProductListEntity item) throws Exception;
}
