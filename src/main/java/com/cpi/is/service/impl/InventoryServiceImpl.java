package com.cpi.is.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	    Long fplId = Long.parseLong(json.getString("fplId"));
	    Long skuCode = Long.parseLong(json.getString("skuCode"));
	    Long quantity = Long.parseLong(json.getString("quantity"));
	    String branchId = json.getString("branchId");

	    // Assuming dateFinished is in a specific string format, e.g., "yyyy-MM-dd"
	    String dateFinishedStr = json.getString("dateFinished");
	    Date dateFinished = null;
	    try {
	        // Change the format to match the one used in your JSON
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFinished = dateFormat.parse(dateFinishedStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        // Handle the exception, possibly with default value or rethrow
	    }

	    return new InventoryEntity(fplId, skuCode, quantity, branchId, dateFinished);
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
