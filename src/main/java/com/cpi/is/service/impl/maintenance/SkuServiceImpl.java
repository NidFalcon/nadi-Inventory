package com.cpi.is.service.impl.maintenance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.cpi.is.dao.impl.maintenance.SkuDAOImpl;
import com.cpi.is.entity.maintenance.SkuEntity;
import com.cpi.is.service.maintenance.SkuService;
import com.cpi.is.validation.JsonValidate;

public class SkuServiceImpl implements SkuService, JsonValidate {

    private SkuDAOImpl skuDAO;

    public SkuDAOImpl getSkuDAO() {
        return skuDAO;
    }

    public void setSkuDAO(SkuDAOImpl skuDAO) {
        this.skuDAO = skuDAO;
    }

    private SkuEntity jsonToEntity(JSONObject json) {
    	validateJson(json);
        String skuCode = json.getString("skuCode");
        String skuName = json.getString("skuName");
        String unitOfMeasurement = json.getString("unitOfMeasurement");
        String isActive = json.getString("isActive");

        return new SkuEntity(skuCode, skuName, unitOfMeasurement, isActive);
    }

    @Override
    public List<SkuEntity> getSku() throws Exception {
        return skuDAO.getSku();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        return skuDAO.saveItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return skuDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

	@Override
	public void validateJson(JSONObject jsonObject) throws JSONException {
        String[] requiredFields = {"isActive", "skuCode", "skuName", "unitOfMeasurement"};

        for (String field : requiredFields) {
            if (!jsonObject.has(field)) {
                throw new JSONException("Missing required field: " + field);
            }
        }

        String isActive = jsonObject.getString("isActive");
        if (!isActive.equals("y") && !isActive.equals("n")) {
            throw new JSONException("Invalid value for field 'isActive'. Expected 'y' or 'n'.");
        }

        String skuCode = jsonObject.getString("skuCode");
        if (skuCode.isEmpty()) {
            throw new JSONException("Field 'skuCode' cannot be empty.");
        }

        String skuName = jsonObject.getString("skuName");
        if (skuName.isEmpty()) {
            throw new JSONException("Field 'skuName' cannot be empty.");
        }

        String unitOfMeasurement = jsonObject.getString("unitOfMeasurement");
        if (unitOfMeasurement.isEmpty()) {
            throw new JSONException("Field 'unitOfMeasurement' cannot be empty.");
        }
	}
}
