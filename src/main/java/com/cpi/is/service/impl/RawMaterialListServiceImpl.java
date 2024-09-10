package com.cpi.is.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import com.cpi.is.exception.InvalidJsonException;
import com.cpi.is.service.RawMaterialListService;
import com.cpi.is.validation.DateValidate;
import com.cpi.is.validation.JsonValidate;
import com.cpi.is.validation.ValidationUtil;

public class RawMaterialListServiceImpl implements RawMaterialListService, JsonValidate{
	
	private RawMaterialListDAOImpl rawMaterialListDAO = new RawMaterialListDAOImpl();
	private RawMaterialDAOImpl rawMaterialDAO = new RawMaterialDAOImpl();
	
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
	public void validateJson(JSONObject jsonObject) throws InvalidJsonException {

		String requiredFields[] = {"materialListId", "materialCode", "quantity", "userId", "dateRecieve", "branchId"};
	    ValidationUtil.checkFields(requiredFields, jsonObject);

	    ValidationUtil.isValidDate(jsonObject.getString("dateRecieve"));
	    
	    // Validate quantity
	    String quantityStr = jsonObject.get("quantity").toString();
	    
	    ValidationUtil.checkNumber(quantityStr);

	    try {
			if (!isValidForeignKey(jsonObject.getString("materialCode"))){
			    throw new InvalidJsonException("materialCode value is not a valid Foreign Key");
			}
		} catch (Exception e) {
			if (e instanceof InvalidJsonException )
			e.printStackTrace();
		}
	    	
	}

	private boolean isValidForeignKey(String foreignKey) throws Exception {
	   rawMaterialDAO.getRawMaterialById(foreignKey);
	   return true;
   };
	
}