package com.cpi.is.service.inventory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.inventory.FinishedProductListEntity;


public interface FinishedProductListService {
	
	List<FinishedProductListEntity> getFinishedProductList(Long branchId) throws Exception;
	String saveProduct(HttpServletRequest request) throws Exception;
	String deleteProduct(HttpServletRequest request) throws Exception;
}
