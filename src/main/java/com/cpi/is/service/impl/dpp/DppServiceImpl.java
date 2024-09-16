package com.cpi.is.service.impl.dpp;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;

import com.cpi.is.dao.impl.dpp.DppDAOImpl;
import com.cpi.is.dao.impl.maintenance.SkuDAOImpl;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.entity.dpp.DppEntity;
import com.cpi.is.exception.InvalidJsonException;
import com.cpi.is.service.dpp.DppService;
import com.cpi.is.util.ValidationUtil;
import com.cpi.is.validation.JsonValidate;

public class DppServiceImpl implements DppService, JsonValidate {

    private DppDAOImpl dppDAO;
    private SkuDAOImpl skuDAO = new SkuDAOImpl();

    public DppDAOImpl getDppDAO() {
        return dppDAO;
    }

    public void setDppDAO(DppDAOImpl dppDAO) {
        this.dppDAO = dppDAO;
    }

    private DppEntity jsonToEntity(JSONObject json) {
        Long dppId = json.has("dppId") && !json.isNull("dppId") ? json.getLong("dppId") : null;
        Date productionDate = json.has("productionDate") ? Date.valueOf(json.getString("productionDate")) : null;
        Integer quantity = json.has("quantity") ? json.getInt("quantity") : null;
        String status = json.has("status") ? json.getString("status") : null;
        
        Long branchId = json.has("branchId") ? json.getLong("branchId") : null;
        String skuCode = json.has("skuCode") ? json.getString("skuCode") : null;

        return new DppEntity(dppId, productionDate, quantity, status, branchId, skuCode);
    }

    @Override
    public List<DppEntity> getDpp(Integer branchId) throws Exception {
        return dppDAO.getDpp(branchId);
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
    	JSONObject newDppJson = new JSONObject(request.getParameter("item"));
    	UserEntity user = (UserEntity) request.getSession().getAttribute("user");
    	newDppJson.put("branchId", user.getBranchId());
    	
    	String operation = request.getParameter("operation");
    	validateJson(newDppJson, operation);
    	
        return dppDAO.saveItem(jsonToEntity(newDppJson));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return dppDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
	
	@Override
	public void validateJson(JSONObject jsonObject, String operation) throws InvalidJsonException {

	    String[] requiredFields = {"dppId", "productionDate", "quantity", "status", "branchId", "skuCode"};
	    ValidationUtil.checkFields(requiredFields, jsonObject);
	    ValidationUtil.isValidDate(jsonObject.getString("productionDate"));
	    ValidationUtil.checkNumber(jsonObject.get("quantity").toString());
	    validateStatus(jsonObject.getString("status"));
	    checkForeignKey(jsonObject.getString("skuCode"));

	    if ("add".equals(operation)) {
	        if (jsonObject.has("dppId") && jsonObject.get("dppId") != JSONObject.NULL) {
	            throw new InvalidJsonException("Invalid ID");
	        }
	    } else if ("update".equals(operation)) {
	        if (jsonObject.isNull("dppId") || jsonObject.get("dppId").toString().isEmpty()) {
	            throw new InvalidJsonException("Invalid ID");
	        }
	    }
	}


	public boolean checkForeignKey (String jsonObject) throws InvalidJsonException {
		try {
			if (skuDAO.getSkuById(jsonObject) == null){
				throw new InvalidJsonException("Invalid SKU Code");
			}
			
		} catch (InvalidJsonException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new InvalidJsonException("An unexpected error occurred while validating SKU Code");
	    }
		return true;
	}
	
	private static final Set<String> VALID_STATUSES = new HashSet<String>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
        add("Planned");
        add("In Progress");
        add("Finished");
    }};
	
	public void validateStatus(String status) throws InvalidJsonException {
		try {
			if (!VALID_STATUSES.contains(status)) {
	            throw new InvalidJsonException("Invalid Status");
	        }
		} catch (Exception e) {
			if (e instanceof InvalidJsonException) {
				throw (InvalidJsonException) e;
			}
			e.printStackTrace();
		}
	}
	
}
