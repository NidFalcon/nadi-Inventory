package com.cpi.is.service.maintenance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.maintenance.BranchEntity;

public interface BranchService {
	
	List<BranchEntity> getBranch() throws Exception;
	String saveItem(HttpServletRequest request) throws Exception;
	String deleteItem(HttpServletRequest request) throws Exception;

}
