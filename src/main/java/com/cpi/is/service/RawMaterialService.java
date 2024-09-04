package com.cpi.is.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.RawMaterialEntity;

public interface RawMaterialService {

    List<RawMaterialEntity> getRawMaterial() throws Exception;
    String saveItem(HttpServletRequest request) throws Exception;
    String deleteItem(HttpServletRequest request) throws Exception;
}
