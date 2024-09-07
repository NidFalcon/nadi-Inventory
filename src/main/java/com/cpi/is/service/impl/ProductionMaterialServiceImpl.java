package com.cpi.is.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.cpi.is.dao.impl.ProductionMaterialDAOImpl;
import com.cpi.is.entity.ProductionMaterialEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.ProductionMaterialService;

public class ProductionMaterialServiceImpl implements ProductionMaterialService {

    private ProductionMaterialDAOImpl productionMaterialDAO;

    public ProductionMaterialDAOImpl getProductionMaterialDAO() {
        return productionMaterialDAO;
    }

    public void setProductionMaterialDAO(ProductionMaterialDAOImpl productionMaterialDAO) {
        this.productionMaterialDAO = productionMaterialDAO;
    }

    private ProductionMaterialEntity jsonToEntity(JSONObject json) throws Exception {
    	Long pmId = null;
        Long dppId = null;
        String materialCode = null;
        Integer quantityToUse = null;

        if (json.has("pmId") && json.has("dppId") && json.has("materialCode") && json.has("quantityToUse")) {
            pmId = !json.isNull("pmId") ? json.getLong("pmId") : null;
            dppId = json.getLong("dppId");
            materialCode = json.getString("materialCode");
            quantityToUse = json.getInt("quantityToUse");
        } else if (json.has("pmId")) { 
            pmId = json.getLong("pmId");
        } else {
            throw new Exception("JSON malformed: Missing required fields");
        }

        return new ProductionMaterialEntity(pmId, dppId, materialCode, quantityToUse);
    }

    @Override
    public List<ProductionMaterialEntity> getProductionMaterial() throws Exception {
        return productionMaterialDAO.getProductionMaterial();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        JSONObject newMaterialJson = new JSONObject(request.getParameter("item"));
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");

        return productionMaterialDAO.saveItem(jsonToEntity(newMaterialJson));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return productionMaterialDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
    
	@Override
	public String saveBulkItems(HttpServletRequest request) throws Exception {
		JSONArray jsonArr = new JSONArray(request.getParameter("item"));
		List<ProductionMaterialEntity> productionMaterial = new ArrayList<ProductionMaterialEntity>();
		for (int i = 0; i < jsonArr.length(); i++) {
			productionMaterial.add(jsonToEntity(jsonArr.getJSONObject(i)));
		}
		return productionMaterialDAO.saveBulkItems(productionMaterial);
	}
}
