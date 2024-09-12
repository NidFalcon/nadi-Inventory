package com.cpi.is.service.impl.dpp;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.cpi.is.dao.impl.dpp.ProductionMaterialDAOImpl;
import com.cpi.is.entity.dpp.ProductionMaterialEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.dpp.ProductionMaterialService;

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
        Long materialListId = null;
        String materialCode = null;
        Integer quantityToUse = null;

        // Log the incoming JSON for debugging
        System.out.println("Incoming JSON: " + json.toString());

        if (json.has("pmId")) {
            pmId = json.isNull("pmId") ? null : json.optLong("pmId");
        }

        // If pmId is provided, and no other fields are required for deletion, return entity with only pmId
        if (pmId != null && !json.has("dppId") && !json.has("materialListId") && !json.has("materialCode") && !json.has("quantityToUse")) {
            return new ProductionMaterialEntity(pmId);
        }

        // Handle other fields for create or update
        if (json.has("dppId")) {
            dppId = json.optLong("dppId");
        }

        if (json.has("materialListId")) {
            materialListId = json.optLong("materialListId");
        }

        if (json.has("materialCode")) {
            materialCode = json.optString("materialCode", null);
        }

        if (json.has("quantityToUse")) {
            quantityToUse = json.optInt("quantityToUse", 0);
        }

        // Validate required fields for create or update
        if (dppId == null || materialListId == null || materialCode == null || quantityToUse == null) {
            throw new Exception("JSON malformed: Missing required fields");
        }

        return new ProductionMaterialEntity(pmId, dppId, materialListId, materialCode, quantityToUse);
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
        JSONObject itemJson = new JSONObject(request.getParameter("item"));
        ProductionMaterialEntity entity = jsonToEntity(itemJson);

        // Ensure that entity only has pmId set for deletion
        if (entity.getPmId() == null) {
            throw new Exception("JSON malformed: Missing pmId");
        }

        return productionMaterialDAO.deleteItem(entity);
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
