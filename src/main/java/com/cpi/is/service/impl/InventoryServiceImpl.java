package com.cpi.is.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.cpi.is.dao.impl.InventoryDAOImpl;
import com.cpi.is.entity.InventoryEntity;
import com.cpi.is.service.InventoryService;

public class InventoryServiceImpl implements InventoryService {

	private InventoryDAOImpl inventoryDAO;
	
	public InventoryDAOImpl getInventoryDAO() {
		return inventoryDAO;
	}

	public void setInventoryDAO(InventoryDAOImpl inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}
	
	private InventoryEntity jsonToEntity(JSONObject json) {
		return new InventoryEntity(
				Long.parseLong(json.getString("inventoryId")), 
				json.getString("description"), 
				Long.parseLong(json.getString("quantity")), 
				json.getString("activeTag"));
	}

	@Override
	public List<InventoryEntity> getInventory() throws Exception {
		return inventoryDAO.getInventory();
	}

	@Override
	public String saveItem(HttpServletRequest request) throws Exception {
		return inventoryDAO.saveItem(
				jsonToEntity(new JSONObject(request.getParameter("item"))));
	}

	@Override
	public String deleteItem(HttpServletRequest request) throws Exception {
		return inventoryDAO.deleteItem(
				jsonToEntity(new JSONObject(request.getParameter("item"))));
	}

}
