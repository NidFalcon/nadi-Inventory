package com.cpi.is.service.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

    private ProductionMaterialEntity jsonToEntity(JSONObject json) {
        Long pmId = json.has("pmId") && !json.isNull("pmId") ? json.getLong("pmId") : null;
        Long dppId = json.has("dppId") ? json.getLong("dppId") : null;
        String materialCode = json.has("materialCode") ? json.getString("materialCode") : null;
        Integer quantityToUse = json.has("quantityToUse") ? json.getInt("quantityToUse") : null;

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

        // If user data is needed for saving, you can add it here or handle it accordingly
        return productionMaterialDAO.saveItem(jsonToEntity(newMaterialJson));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return productionMaterialDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
}
