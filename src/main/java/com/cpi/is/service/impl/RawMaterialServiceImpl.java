package com.cpi.is.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.cpi.is.dao.impl.RawMaterialDAOImpl;
import com.cpi.is.entity.RawMaterialEntity;
import com.cpi.is.service.RawMaterialService;
import com.cpi.is.util.JsonUtil;

public class RawMaterialServiceImpl implements RawMaterialService {

    private RawMaterialDAOImpl rawMaterialDAO;

    public RawMaterialDAOImpl getRawMaterialDAO() {
        return rawMaterialDAO;
    }

    public void setRawMaterialDAO(RawMaterialDAOImpl rawMaterialDAO) {
        this.rawMaterialDAO = rawMaterialDAO;
    }

    private RawMaterialEntity jsonToEntity(JSONObject json) {
    	validateJson(json);
        String materialCode = JsonUtil.sanitize(json.getString("materialCode"));
        String materialName = JsonUtil.sanitize(json.getString("materialName"));
        String unitOfMeasurement = JsonUtil.sanitize(json.getString("unitOfMeasurement"));
        String isActive = json.getString("isActive");

        return new RawMaterialEntity(materialCode, materialName, unitOfMeasurement, isActive);
    }

    @Override
    public List<RawMaterialEntity> getRawMaterial() throws Exception {
        return rawMaterialDAO.getRawMaterial();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        return rawMaterialDAO.saveItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return rawMaterialDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
    
    public static void validateJson(JSONObject jsonObject) throws JSONException {
    	
    	final Pattern BOOLEAN_PATTERN = Pattern.compile("^[yn]$");
    	
        // Validate required fields
        String[] requiredFields = {"materialCode", "materialName", "unitOfMeasurement", "isActive"};
        for (String field : requiredFields) {
            if (!jsonObject.has(field) || jsonObject.isNull(field)) {
                throw new JSONException("Missing required field: " + field);
            }
        }

        // Validate 'materialCode'
        String materialCode = jsonObject.getString("materialCode").trim();
        if (materialCode.isEmpty()) {
            throw new JSONException("Empty 'materialCode'");
        }

        // Validate 'materialName'
        String materialName = jsonObject.getString("materialName").trim();
        if (materialName.isEmpty()) {
            throw new JSONException("Empty 'materialName'");
        }

        // Validate 'unitOfMeasurement'
        String unitOfMeasurement = jsonObject.getString("unitOfMeasurement").trim();
        if (unitOfMeasurement.isEmpty()) {
            throw new JSONException("Empty 'unitOfMeasurement'");
        }

        // Validate 'isActive'
        String isActive = jsonObject.getString("isActive").trim();
        if (!BOOLEAN_PATTERN.matcher(isActive).matches()) {
            throw new JSONException("Invalid 'isActive' value. Must be 'y' or 'n'.");
        }
    }
    
}
