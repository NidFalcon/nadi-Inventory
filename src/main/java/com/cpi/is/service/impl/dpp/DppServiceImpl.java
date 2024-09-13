package com.cpi.is.service.impl.dpp;

import java.sql.Date;
import java.util.List;
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
    private SkuDAOImpl skuDao = new SkuDAOImpl();

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
        return dppDAO.saveItem(jsonToEntity(newDppJson));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return dppDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

	@Override
	public void validateJson(JSONObject jsonObject, String operation) throws InvalidJsonException {

		String requiredFields[] = {"dppId", "productionDate", "quantity", "status", "branchId", "skuCode"};
	    ValidationUtil.checkFields(requiredFields, jsonObject);
	    ValidationUtil.isValidDate(jsonObject.getString("production_date"));
	    
	    // Validate quantity
	    String quantityStr = jsonObject.get("quantity").toString();
	    ValidationUtil.checkNumber(jsonObject.get("dppId").toString());
	    ValidationUtil.checkNumber(quantityStr);
	    
		if ("add".equals(operation)) {
			Long dppId = jsonObject.getLong("dppId");
			if (dppId != null) {
				throw new InvalidJsonException("Primary Key must not have a value for Add Operations");
			}
		} else if ("update".equals(operation)) {
			if (jsonObject.isNull("dppId") || jsonObject.getString("dppId").isEmpty()) {
				throw new InvalidJsonException("Primary Key must not be NULL for Update Operations");
			}
			validatePK(jsonObject.getLong("dppId"));
		    checkForeignKey(jsonObject.getString("skuCode"));
		}
	}

	public boolean checkForeignKey (String jsonObject) throws InvalidJsonException {
		try {
			if (skuDao.getSkuById(jsonObject) == null) {
				throw new InvalidJsonException("Invalid Parameter");
			}
		} catch (Exception e) {
			if (e instanceof InvalidJsonException) {
				throw (InvalidJsonException) e;
			}
			e.printStackTrace();
		}
		return true;
	}
	
	public void validatePK(Long primaryKey) throws InvalidJsonException {
		try {
			if (dppDAO.getDppById(primaryKey) == null) {
				throw new InvalidJsonException("Primary Key must be a Valid Primary Key for Update Operations");
			}
		} catch (Exception e) {
			if (e instanceof InvalidJsonException) {
				throw (InvalidJsonException) e;
			}
			e.printStackTrace();
		}
	}
	
}
