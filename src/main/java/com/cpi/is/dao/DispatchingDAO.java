package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.DispatchingEntity;

public interface DispatchingDAO {
    
    List<DispatchingEntity> getDispatchingByBranchId(Integer branchId) throws Exception; // Add this method
    List<Object[]> getCurrentInventory(Integer branchId) throws Exception;
    String saveItem(DispatchingEntity item) throws Exception;
    String deleteItem(DispatchingEntity item) throws Exception;

}
