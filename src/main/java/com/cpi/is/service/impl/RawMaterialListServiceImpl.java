package com.cpi.is.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.cpi.is.dao.impl.BranchDAOImpl;
import com.cpi.is.dao.impl.RawMaterialDAOImpl;
import com.cpi.is.dao.impl.RawMaterialListDAOImpl;
import com.cpi.is.dao.impl.UserDAOImpl;
import com.cpi.is.entity.RawMaterialListEntity;
import com.cpi.is.service.RawMaterialListService;

public class RawMaterialListServiceImpl{
	
	private RawMaterialListDAOImpl rawMaterialListDAO = new RawMaterialListDAOImpl();
	private RawMaterialDAOImpl rawMaterialDAOImpl = new RawMaterialDAOImpl();
	private UserDAOImpl userDao = new UserDAOImpl();
	private BranchDAOImpl branchDao = new BranchDAOImpl();
	
	
	public RawMaterialListDAOImpl getRawMaterialListDAO() {
		return rawMaterialListDAO;
	}

	public void setRawMaterialListDAO(RawMaterialListDAOImpl rawMaterialListDAO) {
		this.rawMaterialListDAO = rawMaterialListDAO;
	}

	
	private RawMaterialListEntity jsonToEntity(JSONObject json) throws NumberFormatException, JSONException, Exception {
		
		/*JSON Format
		 * 
		 * JSONObject jsonObject = new JSONObject();
				jsonObject.put("materialListId", 0);
		        jsonObject.put("materialCode", "MAT001");
		        jsonObject.put("quantity", 100);
		        jsonObject.put("userId", 3);
		        jsonObject.put("dateRecieve", "2024-08-28");
		        jsonObject.put("branchId", 5);
				
		        RawMaterialListServiceImpl test = new RawMaterialListServiceImpl();
		        
		        test.saveItem(jsonObject);
		 */
		return new RawMaterialListEntity(
				json.getInt("materialListId"),
				rawMaterialDAOImpl.getRawMaterial(json.getString("materialCode")),
				json.getInt("quantity"),
				userDao.getUser(json.getInt("userId")),
				convertStringToSqlDate(json.getString("dateRecieve")),
				branchDao.getBranch(json.getInt("branchId")));
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
	
	public List<RawMaterialListEntity>  getRawMaterialList() throws Exception {
		return rawMaterialListDAO.getRawMaterialList(3);
	}

	public String saveItem(JSONObject test) throws Exception {
		return rawMaterialListDAO.saveItem(jsonToEntity(test));
	}

	public String deleteItem(HttpServletRequest request) throws Exception {
		return rawMaterialListDAO.deleteItem(
				jsonToEntity(new JSONObject(request.getParameter("item"))));
	}
	
}