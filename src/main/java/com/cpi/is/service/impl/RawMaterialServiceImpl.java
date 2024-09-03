package com.cpi.is.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.cpi.is.dao.impl.RawMaterialDAOImpl;
import com.cpi.is.entity.RawMaterialEntity;
import com.cpi.is.service.RawMaterialService;

public class RawMaterialServiceImpl implements RawMaterialService {

    private RawMaterialDAOImpl rawMaterialDAO;

    public RawMaterialDAOImpl getRawMaterialDAO() {
        return rawMaterialDAO;
    }

    public void setRawMaterialDAO(RawMaterialDAOImpl rawMaterialDAO) {
        this.rawMaterialDAO = rawMaterialDAO;
    }

    private RawMaterialEntity jsonToEntity(JSONObject json) {
        String materialCode = json.getString("materialCode");
        String materialName = json.getString("materialName");
        String unitOfMeasurement = json.getString("unitOfMeasurement");
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
}
