package com.cpi.is.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.cpi.is.dao.DispatchingDAO;
import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.entity.inventory.FinishedProductListEntity;
import com.cpi.is.service.DispatchingService;

public class DispatchingServiceImpl implements DispatchingService {

	private DispatchingDAO dispatchingDAO;

	public DispatchingDAO getDispatchingDAO() {
		return dispatchingDAO;
	}

	public void setDispatchingDAO(DispatchingDAO dispatchingDAO) {
		this.dispatchingDAO = dispatchingDAO;
	}

	private DispatchingEntity jsonToEntity(JSONObject json) throws ParseException {
		Long dispatchTrackId = json.has("dispatchTrackId") ? Long.parseLong(json.getString("dispatchTrackId")) : null;
		String dispatchTypeCd = json.optString("dispatchTypeCd");
		Long fplId = Long.parseLong(json.getString("fplId"));
		Integer quantity = Integer.parseInt(json.getString("quantity"));
		Integer branchId = json.has("branchId") ? json.getInt("branchId") : null;
		String destination = json.optString("destination");

		// Assuming dispatchDate is in a specific string format, e.g., "yyyy-MM-dd"
		String dispatchDateStr = json.getString("dispatchDate");
		Date dispatchDate = parseDate(dispatchDateStr);

		return new DispatchingEntity(dispatchTrackId, dispatchTypeCd, fplId, quantity, branchId, destination,
				dispatchDate);
	}

	private Date parseDate(String dateStr) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Ensure strict parsing
		return dateFormat.parse(dateStr);
	}

	@Override
	public List<DispatchingEntity> getDispatchingByBranchId(Integer branchId) throws Exception {
		return dispatchingDAO.getDispatchingByBranchId(branchId);
	}

	@Override
	public List<Object[]> getCurrentInventory(Integer branchId) throws Exception {
		return dispatchingDAO.getCurrentInventory(branchId);
	}

	//@Override
	/*
	 * public String saveItem(HttpServletRequest request,
	 * List<FinishedProductListEntity> finishedProductList) throws Exception {
	 * HttpSession session = request.getSession(); UserEntity user = (UserEntity)
	 * session.getAttribute("user"); JSONObject json = new
	 * JSONObject(request.getParameter("item")); json.put("branchId",
	 * user.getBranchId());
	 * 
	 * DispatchingEntity entity = jsonToEntity(json);
	 * 
	 * // Validate the dispatch entity if (entity.getDispatchDate() == null ||
	 * entity.getQuantity() < 0) { throw new
	 * IllegalArgumentException("Invalid data: date or quantity cannot be null/negative."
	 * ); }
	 * 
	 * return dispatchingDAO.saveItem(entity); }
	 */
	@Override
	public String saveItem(HttpServletRequest request,List<FinishedProductListEntity> finishedProductList) throws Exception {

		// TODO Auto-generated method stub
		String validation = validateData(request);
		String results = "";
		
		if(validation.equals("success")) {
			String quantityValidation = validateQuantity(request, finishedProductList);
			if(quantityValidation.equals("success")) {
				try {
					results = 	dispatchingDAO.saveItem(
							jsonToEntity(new JSONObject(request.getParameter("item"))));
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				return quantityValidation;
			}
			
			if(results.equals("success")) {
				return results;
			} else {
				return "Unable to save Dispatching Data";
			}
		} else {
			return validation;
		}
	}
	
	@Override
	public String deleteItem(HttpServletRequest request) throws Exception {
		DispatchingEntity entity = jsonToEntity(new JSONObject(request.getParameter("item")));

		// Validate the dispatch entity before deletion
		if (entity.getDispatchTrackId() == null) {
			throw new IllegalArgumentException("Dispatch Track ID cannot be null for deletion.");
		}

		return dispatchingDAO.deleteItem(entity);
	}

	public String validateData(HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject(request.getParameter("item"));
		String validation = "success";
		String errorResult = "Please fill-out the dispatching form properly";

		if (!json.has("dispatchTrackId") || !(json.get("dispatchTrackId") instanceof String)) {
			System.out.println("pointer4");
			validation = errorResult;
		} else if (!json.has("dispatchTypeCd") || !(json.get("dispatchTypeCd") instanceof String)) {
			System.out.println("pointer5");
			validation = errorResult;
		} else if (!json.has("fplId") || !(json.get("fplId") instanceof String)) {
			System.out.println("pointer6");
			validation = errorResult;
		} else if (!json.has("quantity") || !(json.get("quantity") instanceof String)) {
			System.out.println("pointer7");
			validation = errorResult;
		} else if (!json.has("destination") || !(json.get("destination") instanceof String)) {
			System.out.println("pointer8");
			validation = errorResult;
		} else if (!json.has("dispatchDate") || !(json.get("dispatchDate") instanceof String)) {
			System.out.println("pointer9");
			validation = errorResult;
		} else if (json.getString("dispatchTrackId").length() < 1 || json.getString("dispatchTrackId").length() > 14) {
			System.out.println("pointer10");
			validation = errorResult;
		} else if (!json.getString("dispatchTrackId").matches("^[0-9]\\d*$")) {
			System.out.println("pointer11");
			validation = errorResult;
		} else if (json.getString("fplId").length() < 1 || json.getString("fplId").length() > 14) {
			System.out.println("pointer12");
			validation = errorResult;
		} else if (!json.getString("fplId").matches("^[1-9]\\d*$")) {
			System.out.println("pointer13");
			validation = errorResult;
		} else if (json.getString("quantity").length() < 1 || json.getString("quantity").length() > 14) {
			System.out.println("pointer15");
			validation = errorResult;
		} else if (!json.getString("quantity").matches("^[0-9]+$")) {
			System.out.println("pointer16");
			validation = errorResult;
		} else if (json.getString("dispatchTypeCd").length() < 1 || json.getString("dispatchTypeCd").length() > 10) {
			System.out.println("pointer17");
			validation = errorResult;
		} else if (json.getString("destination").length() < 1 || json.getString("destination").length() > 50) {
			System.out.println("pointer18");
			validation = errorResult;
		} else {
			try {
				LocalDate.parse(json.getString("dispatchDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			} catch (DateTimeParseException e) {
				System.out.println("pointer19");
				validation = errorResult;
			}
		}

		return validation;
	}
	
	public String validateQuantity(HttpServletRequest request, List<FinishedProductListEntity> finishedProductList) throws Exception {
	    JSONObject json = new JSONObject(request.getParameter("item"));
	    String validation = "Please fill-out the dispatching form correctly";
	    System.out.println("json = " + json);
	    
	    // Fetch current inventory from DAO
	    List<Object[]> currentInventory = getCurrentInventory(Integer.parseInt(json.getString("branchId")));

	    // Fetch dispatched entities for the branch
	    List<DispatchingEntity> dispatchingEntities = getDispatchingByBranchId(Integer.parseInt(json.getString("branchId")));

	    outerloop:
	    for (FinishedProductListEntity finishedProduct : finishedProductList) {
	        if (finishedProduct.getFplId() == Long.parseLong(json.getString("fplId"))) {

	            // Check if dispatch date is valid
	            if (finishedProduct.getDateFinished().getTime() >
	                (new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("dispatchDate")).getTime())) {
	            	System.out.println("pointer3");
	                break outerloop;
	            }

	            // New Dispatch: dispatchTrackId is 0
	            if (Long.parseLong(json.getString("dispatchTrackId")) == 0) {
	                // Compare against current inventory
	                for (Object[] inventoryItem : currentInventory) {
	                    String skuCD = (String) inventoryItem[0]; // SKU Code
	                    Long availableQuantity = (Long) inventoryItem[1]; // Available quantity

	                    if (skuCD.equals(finishedProduct.getSkuCD())) {
	                        // If the available quantity is less than the requested quantity, fail validation
	                        if (availableQuantity < Long.parseLong(json.getString("quantity"))) {
	                        	System.out.println("pointer2");
	                            break outerloop;
	                        } else {
	                            validation = "success";
	                            break outerloop;
	                        }
	                    }
	                }
	            } else {
	                // Existing dispatch: check current inventory and dispatched quantities
	                for (DispatchingEntity dispatchingEntity : dispatchingEntities) {
	                    if (dispatchingEntity.getDispatchTrackId() == Long.parseLong(json.getString("dispatchTrackId"))) {
	                        for (Object[] inventoryItem : currentInventory) {
	                            String skuCD = (String) inventoryItem[0]; // SKU Code
	                            Long availableQuantity = (Long) inventoryItem[1]; // Available quantity

	                            if (skuCD.equals(finishedProduct.getSkuCD())) {
	                                // Add dispatched quantity to current available inventory
	                                Long totalAvailable = availableQuantity + dispatchingEntity.getQuantity();
	                                
	                                // If the total available quantity is less than the requested quantity, fail validation
	                                if (totalAvailable < Long.parseLong(json.getString("quantity"))) {
	                                	System.out.println("pointer1");
	                                    break outerloop;
	                                } else {
	                                    validation = "success";
	                                    System.out.println("Entered successfully");
	                                    break outerloop;
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }

	    return validation;
	}
}