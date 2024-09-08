package com.cpi.is.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.cpi.is.dao.impl.BranchDAOImpl;
import com.cpi.is.dao.impl.RawMaterialDAOImpl;
import com.cpi.is.dao.impl.RawMaterialListDAOImpl;
import com.cpi.is.dao.impl.UserDAOImpl;
import com.cpi.is.entity.RawMaterialListEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.RawMaterialListService;
import com.cpi.is.validation.DateValidate;
import com.cpi.is.validation.JsonValidate;

public class RawMaterialListServiceImpl implements RawMaterialListService, JsonValidate, DateValidate{
	
	private RawMaterialListDAOImpl rawMaterialListDAO = new RawMaterialListDAOImpl();
	
	
	public RawMaterialListDAOImpl getRawMaterialListDAO() {
		return rawMaterialListDAO;
	}

	public void setRawMaterialListDAO(RawMaterialListDAOImpl rawMaterialListDAO) {
		this.rawMaterialListDAO = rawMaterialListDAO;
	}

	
	private RawMaterialListEntity jsonToEntity(JSONObject json) throws NumberFormatException, JSONException, Exception {
		
		return new RawMaterialListEntity(
				json.getInt("materialListId"),
				json.getString("materialCode"),
				json.getInt("quantity"),
				json.getInt("userId"),
				convertStringToSqlDate(json.getString("dateRecieve")),
				json.getInt("branchId"));
	}
	
	private static Date convertStringToSqlDate(String dateString) {
        // Define the format of the input string
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            // Parse the input string into a java.util.Date
            java.util.Date utilDate = inputFormat.parse(dateString);
            
            // Convert java.util.Date to java.sql.Date
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null;
        }
    }
	
	public List<RawMaterialListEntity>  getRawMaterialList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		return rawMaterialListDAO.getRawMaterialList(user.getBranchId());
	}



	@Override
	public String saveRawMaterial(HttpServletRequest request, HttpSession session) throws Exception {
		JSONObject json = new JSONObject(request.getParameter("item"));
		UserEntity user = (UserEntity) session.getAttribute("user");
		json.put("userId", user.getUserId());
		json.put("branchId", user.getBranchId());
		validateJson(json);
		return rawMaterialListDAO.saveRawMaterial(
				jsonToEntity(json));
		//return null;		
	}

	@Override
	public String deleteRawMaterial(HttpServletRequest request) throws Exception {
		System.out.println(request.getParameter("item"));
		return rawMaterialListDAO.deleteRawMaterial(
				jsonToEntity(new JSONObject(request.getParameter("item"))));
	}

	@Override
	public boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date date = (Date) sdf.parse(dateStr);
            return date != null;
        } catch (ParseException e) {
            return false;
        }
	}

	@Override
	public void validateJson(JSONObject jsonObject) throws JSONException {
		final Pattern INTEGER_PATTERN = Pattern.compile("^\\d+$");
		
		// Validate required fields
        String[] requiredFields = {"materialListId", "materialCode", "quantity", "userId", "dateRecieve", "branchId"};
        for (String field : requiredFields) {
            if (!jsonObject.has(field)) {
                throw new JSONException("Missing required field: " + field);
            }
        }

        // Validate quantity
        String quantity = jsonObject.getString("quantity");
        if (!INTEGER_PATTERN.matcher(quantity).matches() || Integer.parseInt(quantity) < 0 || Integer.parseInt(quantity) > Integer.MAX_VALUE) {
            throw new JSONException("Invalid value for field 'quantity'. Must be a non-negative integer within the integer range.");
        }

        // Validate dateRecieve
        String dateRecieve = jsonObject.getString("dateRecieve");
        if (!isValidDate(dateRecieve)) {
            throw new JSONException("Invalid date format for field 'dateRecieve'. Expected format: " + "yyyy-MM-dd");
        }
	}
	
}