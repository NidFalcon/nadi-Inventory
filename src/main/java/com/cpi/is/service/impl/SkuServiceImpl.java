package com.cpi.is.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.cpi.is.dao.impl.SkuDAOImpl;
import com.cpi.is.entity.SkuEntity;
import com.cpi.is.service.SkuService;

public class SkuServiceImpl implements SkuService {

    private SkuDAOImpl skuDAO;

    public SkuDAOImpl getSkuDAO() {
        return skuDAO;
    }

    public void setSkuDAO(SkuDAOImpl skuDAO) {
        this.skuDAO = skuDAO;
    }

    private SkuEntity jsonToEntity(JSONObject json) {
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
}