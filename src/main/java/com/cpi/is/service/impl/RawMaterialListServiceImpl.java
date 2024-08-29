package com.cpi.is.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.dao.impl.BranchDAOImpl;
import com.cpi.is.dao.impl.RawMaterialDAOImpl;
import com.cpi.is.dao.impl.RawMaterialListDAOImpl;
import com.cpi.is.dao.impl.UserDAOImpl;
import com.cpi.is.entity.RawMaterialListEntity;

public class RawMaterialListServiceImpl{
	
	private RawMaterialListDAOImpl rawMaterialListDAO;
	private RawMaterialDAOImpl rawMaterialDAO;
	private UserDAOImpl userDAO;
	private BranchDAOImpl branchDAO;

	public RawMaterialListDAOImpl getRawMaterialListDAO() {
		return rawMaterialListDAO;
	}

	public void setRawMaterialListDAO(RawMaterialListDAOImpl rawMaterialListDAO) {
		this.rawMaterialListDAO = rawMaterialListDAO;
	}

	public RawMaterialDAOImpl getRawMaterialDAO() {
		return rawMaterialDAO;
	}

	public void setRawMaterialDAO(RawMaterialDAOImpl rawMaterialDAO) {
		this.rawMaterialDAO = rawMaterialDAO;
	}

	public UserDAOImpl getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	public BranchDAOImpl getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAOImpl branchDAO) {
		this.branchDAO = branchDAO;
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
		        
		        {
		        	"materialListId" : "0",
		        	"materialCode" : "MAT001",
		        	"quantity": "100",
		        	"userId": "3",
		        	"dateRecieve" : "2024-08-28",
		        	"branchId": "5"
		        }
				
		        RawMaterialListServiceImpl test = new RawMaterialListServiceImpl();
		        
		        test.saveItem(jsonObject);
		 */
		return new RawMaterialListEntity(
				json.getInt("materialListId"),
				rawMaterialDAO.getRawMaterial(json.getString("materialCode")),
				json.getInt("quantity"),
				userDAO.getUser(json.getInt("userId")),
				convertStringToSqlDate(json.getString("dateRecieve")),
				branchDAO.getBranch(json.getInt("branchId")));
	}
	
	private static Date convertStringToSqlDate(String dateString) {
        // Define the format of the input string
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        
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

	public String saveItem(HttpServletRequest request) throws Exception {
		return rawMaterialListDAO.saveItem(jsonToEntity(new JSONObject(request.getParameter("item"))));
	}

	public String deleteItem(HttpServletRequest request) throws Exception {
		return rawMaterialListDAO.deleteItem(jsonToEntity(new JSONObject(request.getParameter("item"))));
	}
	
}
