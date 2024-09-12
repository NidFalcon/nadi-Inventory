package com.cpi.is.service.inventory;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.inventory.FinishedProductListEntity;

public interface FinishedProductListService {
    
	List<FinishedProductListEntity> getFinishedProductList(Integer branchId) throws Exception;
    String saveItem(HttpServletRequest request) throws Exception;
    String deleteItem(HttpServletRequest request) throws Exception;
}