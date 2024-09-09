package com.cpi.is.service.impl;

import java.util.List;
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
        // Validate 'materialCode'
        if (!jsonObject.has("materialCode") || jsonObject.isNull("materialCode")) {
            throw new JSONException("Missing or null 'materialCode'");
        }
        String materialCode = jsonObject.getString("materialCode").trim();
        if (materialCode.isEmpty()) {
            throw new JSONException("Empty 'materialCode'");
        }

        // Validate 'materialName'
        if (!jsonObject.has("materialName") || jsonObject.isNull("materialName")) {
            throw new JSONException("Missing or null 'materialName'");
        }
        String materialName = jsonObject.getString("materialName").trim();
        if (materialName.isEmpty()) {
            throw new JSONException("Empty 'materialName'");
        }

        // Validate 'unitOfMeasurement'
        if (!jsonObject.has("unitOfMeasurement") || jsonObject.isNull("unitOfMeasurement")) {
            throw new JSONException("Missing or null 'unitOfMeasurement'");
        }
        String unitOfMeasurement = jsonObject.getString("unitOfMeasurement").trim();
        if (unitOfMeasurement.isEmpty()) {
            throw new JSONException("Empty 'unitOfMeasurement'");
        }

        // Validate 'isActive'
        if (!jsonObject.has("isActive") || jsonObject.isNull("isActive")) {
            throw new JSONException("Missing or null 'isActive'");
        }
        String isActive = jsonObject.getString("isActive").trim();
        if (!isActive.equals("y") && !isActive.equals("n")) {
            throw new JSONException("Invalid 'isActive' value");
        }
    }
    
}
