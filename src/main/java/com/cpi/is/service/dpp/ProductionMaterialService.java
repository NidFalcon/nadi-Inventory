package com.cpi.is.service.dpp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.dpp.ProductionMaterialEntity;

public interface ProductionMaterialService {

    List<ProductionMaterialEntity> getProductionMaterial() throws Exception;
    String saveItem(HttpServletRequest request) throws Exception;
    String saveBulkItems(HttpServletRequest request) throws Exception;
    String deleteItem(HttpServletRequest request) throws Exception;
}
