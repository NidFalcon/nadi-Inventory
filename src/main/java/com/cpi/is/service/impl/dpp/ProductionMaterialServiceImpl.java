package com.cpi.is.service.impl.dpp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import com.cpi.is.dao.impl.dpp.ProductionMaterialDAOImpl;
import com.cpi.is.dao.impl.inventory.RawMaterialListDAOImpl;
import com.cpi.is.dao.impl.maintenance.RawMaterialDAOImpl;
import com.cpi.is.entity.dpp.ProductionMaterialEntity;
import com.cpi.is.exception.InvalidJsonException;
import com.cpi.is.service.dpp.ProductionMaterialService;
import com.cpi.is.util.ValidationUtil;

public class ProductionMaterialServiceImpl implements ProductionMaterialService {

    private ProductionMaterialDAOImpl productionMaterialDAO;
    private RawMaterialDAOImpl rawMaterialDAO = new RawMaterialDAOImpl();
    private RawMaterialListDAOImpl rawMaterialListDAO = new RawMaterialListDAOImpl();

    public ProductionMaterialDAOImpl getProductionMaterialDAO() {
        return productionMaterialDAO;
    }

    public void setProductionMaterialDAO(ProductionMaterialDAOImpl productionMaterialDAO) {
        this.productionMaterialDAO = productionMaterialDAO;
    }

    private ProductionMaterialEntity jsonToEntity(JSONObject json) throws Exception {
        Long pmId = json.has("pmId") && !json.isNull("pmId") ? json.getLong("pmId") : null;
        Long dppId = json.has("dppId") && !json.isNull("dppId") ? json.getLong("dppId") : null;
        Long materialListId = json.has("materialListId") && !json.isNull("materialListId") ? json.getLong("materialListId") : null;
        String materialCode = json.has("materialCode") ? json.getString("materialCode") : null;
        Integer quantityToUse = json.has("quantityToUse") && !json.isNull("quantityToUse") ? json.getInt("quantityToUse") : null;
        
        return new ProductionMaterialEntity(pmId, dppId, materialListId, materialCode, quantityToUse);
    }

    @Override
    public List<ProductionMaterialEntity> getProductionMaterial() throws Exception {
        return productionMaterialDAO.getProductionMaterial();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        JSONObject newMaterialJson = new JSONObject(request.getParameter("item"));
        return productionMaterialDAO.saveItem(jsonToEntity(newMaterialJson));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        JSONObject itemJson = new JSONObject(request.getParameter("item"));
        ProductionMaterialEntity entity = jsonToEntity(itemJson);

        if (entity.getPmId() == null) {
            throw new Exception("Invalid ID");
        }

        return productionMaterialDAO.deleteItem(entity);
    }

    @Override
    public String saveBulkItems(HttpServletRequest request) throws Exception {
        JSONArray jsonArr = new JSONArray(request.getParameter("item"));
        String operation = request.getParameter("operation");
        List<ProductionMaterialEntity> productionMaterialList = new ArrayList<>();
        Set<Long> materialListIdSet = new HashSet<>();
        
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject json = jsonArr.getJSONObject(i);
            Long materialListId = json.has("materialListId") && !json.isNull("materialListId") ? json.getLong("materialListId") : null;
            
            if (materialListId != null && !materialListIdSet.add(materialListId)) {
                throw new InvalidJsonException("Duplicate material found");
            }
            
            validateBulkItemsJson(json, operation);
            validateStockQuantity(json, operation);
            productionMaterialList.add(jsonToEntity(json));
        }

        return productionMaterialDAO.saveBulkItems(productionMaterialList);
    }

    private void validateBulkItemsJson(JSONObject jsonObject, String operation) throws InvalidJsonException {
        String[] requiredFields = {"dppId", "materialListId", "materialCode", "quantityToUse"};
        ValidationUtil.checkFields(requiredFields, jsonObject);

        if (jsonObject.has("quantityToUse") && !jsonObject.isNull("quantityToUse")) {
            ValidationUtil.checkNumber(jsonObject.get("quantityToUse").toString());
        }

        validateMaterialCode(jsonObject.getString("materialCode"));

        if ("add".equals(operation)) {
            if (jsonObject.has("pmId") && jsonObject.get("pmId") != JSONObject.NULL && !jsonObject.get("pmId").toString().isEmpty()) {
                throw new InvalidJsonException("Invalid ID");
            }
        } else if ("update".equals(operation)) {
            if (!jsonObject.has("pmId") || jsonObject.isNull("pmId") || jsonObject.get("pmId").toString().isEmpty()) {
                throw new InvalidJsonException("Invalid ID");
            } else {
                try {
                    Long.parseLong(jsonObject.get("pmId").toString()); 
                } catch (NumberFormatException e) {
                    throw new InvalidJsonException("Invalid ID");
                }
            }

            if (!jsonObject.has("dppId") || jsonObject.isNull("dppId") || jsonObject.get("dppId").toString().isEmpty()) {
                throw new InvalidJsonException("Invalid ID");
            } else {
                try {
                    Long.parseLong(jsonObject.get("dppId").toString()); 
                } catch (NumberFormatException e) {
                    throw new InvalidJsonException("Invalid ID");
                }
            }
        }
    }

    private void validateStockQuantity(JSONObject jsonObject, String operation) throws InvalidJsonException {
        try {
            Long materialListId = jsonObject.has("materialListId") && !jsonObject.isNull("materialListId") ? jsonObject.getLong("materialListId") : null;
            Integer quantityToUse = jsonObject.has("quantityToUse") && !jsonObject.isNull("quantityToUse") ? jsonObject.getInt("quantityToUse") : null;
            Integer currentStock = rawMaterialListDAO.getRawMaterialListById(materialListId).getQuantity();

            if ("add".equals(operation)) {
                if (quantityToUse > currentStock) {
                    throw new InvalidJsonException("Quantity to use exceeds available stock");
                }
            } else if ("update".equals(operation)) {
                Long pmId = jsonObject.has("pmId") && !jsonObject.isNull("pmId") ? jsonObject.getLong("pmId") : null;
                Integer oldQuantityToUse = (pmId != null) ? productionMaterialDAO.getProductionMaterialById(pmId).getQuantityToUse() : 0;

                Integer totalQuantity = oldQuantityToUse + currentStock;

                if (quantityToUse > totalQuantity) {
                    throw new InvalidJsonException("Quantity to use exceeds available stock");
                } 
            }
        } catch (InvalidJsonException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidJsonException("An unexpected error occurred while validating stock quantities");
        }
    }


    public boolean validateMaterialCode(String materialCode) throws InvalidJsonException {
        try {
            if (rawMaterialDAO.getRawMaterialById(materialCode) == null) {
                throw new InvalidJsonException("Invalid Material Code");
            }
        } catch (InvalidJsonException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidJsonException("An unexpected error occurred while validating Material Code");
        }
        return true;
    }
}
