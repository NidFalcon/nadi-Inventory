package com.cpi.is.dao.dpp;

import java.util.List;

import com.cpi.is.entity.dpp.ProductionMaterialEntity;

public interface ProductionMaterialDAO {
    
    List<ProductionMaterialEntity> getProductionMaterial() throws Exception;
    String saveItem(ProductionMaterialEntity item) throws Exception;
    String saveBulkItems(List<ProductionMaterialEntity> item) throws Exception;
    String deleteItem(ProductionMaterialEntity item) throws Exception;
}
