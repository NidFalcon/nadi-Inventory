package com.cpi.is.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.DispatchingEntity;

public interface DispatchingService {
    
	List<DispatchingEntity> getDispatchingByBranch(Integer branchId) throws Exception; // Add this method
	List<Object[]> getCurrentInventory() throws Exception;
	String saveItem(HttpServletRequest request) throws Exception;
    String deleteItem(HttpServletRequest request) throws Exception;
}
