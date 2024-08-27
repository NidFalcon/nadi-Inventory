package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.InventoryEntity;

public interface InventoryDAO {
	
	List<InventoryEntity> getInventory() throws Exception;
	String saveItem(InventoryEntity item) throws Exception;
	String deleteItem(InventoryEntity item) throws Exception;
	
}
