package com.cpi.is.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.entity.inventory.FinishedProductListEntity;

public interface DispatchingService {
    
	List<DispatchingEntity> getDispatchingByBranchId(Integer branchId) throws Exception; // Add this method
	List<Object[]> getCurrentInventory(Integer branchId) throws Exception;
    String deleteItem(HttpServletRequest request) throws Exception;
	String saveItem(HttpServletRequest request, List<FinishedProductListEntity> finishedProductList) throws Exception;
}
