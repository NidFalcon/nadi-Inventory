package com.cpi.is.service.impl.maintenance;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.cpi.is.dao.maintenance.BranchDAO;
import com.cpi.is.entity.maintenance.BranchEntity;
import com.cpi.is.service.maintenance.BranchService;
import com.cpi.is.validation.JsonValidate;

public class BranchServiceImpl implements BranchService, JsonValidate {

    private BranchDAO branchDAO;
    
    public BranchDAO getBranchDAO() {
        return branchDAO;
    }

    public void setBranchDAO(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    private BranchEntity jsonToEntity(JSONObject json) {
        Long branchId = json.has("branchId") && !json.isNull("branchId") ? json.getLong("branchId") : null;
        String branchName = json.getString("branchName");
        String isActive = json.getString("isActive");

        return new BranchEntity(branchId, branchName, isActive);
    }

    @Override
    public List<BranchEntity> getBranch() throws Exception {
        return branchDAO.getBranch();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        return branchDAO.saveItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return branchDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

	@Override
	public void validateJson(JSONObject jsonObject) throws JSONException {
		// Check if the JSONObject contains all required keys
	    if (!jsonObject.has("branchId")) {
	        throw new JSONException("Missing required field: branchId");
	    }
	    if (!jsonObject.has("branchName")) {
	        throw new JSONException("Missing required field: branchName");
	    }
	    if (!jsonObject.has("isActive")) {
	        throw new JSONException("Missing required field: isActive");
	    }

	    // Validate branchId is an integer
	    if (!jsonObject.get("branchId").getClass().equals(Integer.class)) {
	        throw new JSONException("Invalid type for field: branchId. Expected Integer.");
	    }

	    // Validate branchName is a string
	    if (!jsonObject.get("branchName").getClass().equals(String.class)) {
	        throw new JSONException("Invalid type for field: branchName. Expected String.");
	    }

	    // Validate isActive is a string and has correct value
	    String isActive = jsonObject.getString("isActive");
	    if (!isActive.equals("y") && !isActive.equals("n")) {
	        throw new JSONException("Invalid value for field: isActive. Expected 'y' or 'n'.");
	    }
		
	}
}
