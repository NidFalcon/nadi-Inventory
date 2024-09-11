package com.cpi.is.dao.inventory;

import java.util.List;

import com.cpi.is.entity.inventory.FinishedProductListEntity;

public interface FinishedProductListDAO {
    
    List<FinishedProductListEntity> getFinishedProductList(Integer branchId) throws Exception; // Fixed method name
    String saveItem(FinishedProductListEntity item) throws Exception; // Fixed parameter type
    String deleteItem(FinishedProductListEntity item) throws Exception; // Fixed parameter type

}
