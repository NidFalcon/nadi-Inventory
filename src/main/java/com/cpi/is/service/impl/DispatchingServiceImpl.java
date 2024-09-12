package com.cpi.is.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.cpi.is.dao.DispatchingDAO;
import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.entity.UserEntity;
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

        return new DispatchingEntity(dispatchTrackId, dispatchTypeCd, fplId, quantity, branchId, destination, dispatchDate);
    }

    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Ensure strict parsing
        return dateFormat.parse(dateStr);
    }
    
    public String validateData(HttpServletRequest request) throws Exception{
		JSONObject json = new JSONObject(request.getParameter("data"));
		String validation = "success";
		String errorResult = "Please fill-out the dispatching form properly";
		
		if (!json.has("dispatchTrackId") || !(json.get("dispatchTrackId") instanceof String)) {
			validation = errorResult;
		} else if (!json.has("dispatchTypeCd") || !(json.get("dispatchTypeCd") instanceof String)) {
			validation = errorResult;
		} else if (!json.has("fplId") || !(json.get("fplId") instanceof String)) {
			validation = errorResult;
		} else if (!json.has("quantity") || !(json.get("quantity") instanceof String)) {
			validation = errorResult;
		} else if (!json.has("destination") || !(json.get("destination") instanceof String)) {
			validation = errorResult;
		} else if (!json.has("dispatchDate") || !(json.get("dispatchDate") instanceof String)) {
			validation = errorResult;
		} else if (json.getString("dispatchTrackId").length() < 1 || json.getString("dispatchTrackId").length() > 14) {
			validation = errorResult;
		} else if (!json.getString("dispatchTrackId").matches("^[0-9]\\d*$")) {
			validation = errorResult;
		} else if (json.getString("fplId").length() < 1 || json.getString("fplId").length() > 14) {
			validation = errorResult;
		} else if (!json.getString("fplId").matches("^[1-9]\\d*$")) {
			validation = errorResult;
		} else if (json.getString("quantity").length() < 1 || json.getString("quantity").length() > 14) {
			validation = errorResult;
		} else if (!json.getString("quantity").matches("^[0-9]+$")) {
			validation = errorResult;
		} else if (json.getString("dispatchTypeCd").length() < 1 || json.getString("dispatchTypeCd").length() > 10) {
			validation = errorResult;
		} else if (json.getString("destination").length() < 1 || json.getString("destination").length() > 50) {
			validation = errorResult;
		} else {
	        try {
	        	LocalDate.parse(json.getString("dispatchDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        } catch (DateTimeParseException e) {
	        	validation = errorResult;
	        }
		}
		
		return validation;
	}

    @Override
    public List<DispatchingEntity> getDispatchingByBranch(Integer branchId) throws Exception {
        return dispatchingDAO.getDispatchingByBranchId(branchId);
    }

    @Override
    public List<Object[]> getCurrentInventory(Integer branchId) throws Exception {
        return dispatchingDAO.getCurrentInventory(branchId);
    }
    
    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        JSONObject json = new JSONObject(request.getParameter("item"));
        json.put("branchId", user.getBranchId());
        
        DispatchingEntity entity = jsonToEntity(json);
        
        // Validate the dispatch entity
        if (entity.getDispatchDate() == null || entity.getQuantity() < 0) {
            throw new IllegalArgumentException("Invalid data: date or quantity cannot be null/negative.");
        }
        
        return dispatchingDAO.saveItem(entity);
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
}
