package com.cpi.is.service.dpp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.dpp.DppEntity;

public interface DppService {

    List<DppEntity> getDpp(Long branchId) throws Exception;
    String saveItem(HttpServletRequest request) throws Exception;
    String deleteItem(HttpServletRequest request) throws Exception;
}
