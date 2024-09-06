package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.entity.ReportsEntity;

public interface DispatchingDAO {
    
    List<DispatchingEntity> getDispatchingByBranchId(Integer branchId) throws Exception; // Add this method
    List<Object[]> getCurrentInventory() throws Exception;
    String saveItem(DispatchingEntity item) throws Exception;
    String deleteItem(DispatchingEntity item) throws Exception;

}
