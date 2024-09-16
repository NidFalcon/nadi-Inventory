package com.cpi.is.service.impl.inventory;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.cpi.is.dao.impl.inventory.FinishedProductListDAOImpl;
import com.cpi.is.entity.inventory.FinishedProductListEntity;
import com.cpi.is.service.inventory.FinishedProductListService;


public class FinishedProductListServiceImpl implements FinishedProductListService{
	
	private FinishedProductListDAOImpl finishedProductListDAO;

	public FinishedProductListDAOImpl getFinishedProductListDAO() {
		return finishedProductListDAO;
	}

	public void setFinishedProductListDAO(FinishedProductListDAOImpl finishedProductListDAO) {
		this.finishedProductListDAO = finishedProductListDAO;
	}
	
	private FinishedProductListEntity jsonToEntity(JSONObject json) throws NumberFormatException, JSONException, Exception{	
		return new FinishedProductListEntity (
		        json.getLong("fplId"),
		        json.getString("skuCD"),
				json.getInt("quantity"),
		        json.getInt("branchId"),
		        convertStringToSqlDate(json.getString("dateFinished")));
				
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
	
	@Override
	public List<FinishedProductListEntity> getFinishedProductList(Long branchId) throws Exception {
		List<FinishedProductListEntity> finishedProductList = finishedProductListDAO.getFinishedProductList(branchId);
		return finishedProductList;
	}

	@Override
	public String saveProduct(HttpServletRequest request) throws Exception {
		return finishedProductListDAO.saveProduct(
				jsonToEntity(new JSONObject(request.getParameter("item"))));
	}

	@Override
	public String deleteProduct(HttpServletRequest request) throws Exception {
		return finishedProductListDAO.deleteProduct(
				jsonToEntity(new JSONObject(request.getParameter("item"))));
	}
}
