package com.cpi.is.dao.dpp;

import java.util.List;

import com.cpi.is.entity.dpp.DppEntity;

public interface DppDAO {
    
    List<DppEntity> getDpp(Long branchId) throws Exception;
    DppEntity getDppById(Long dppId) throws Exception;
    String saveItem(DppEntity item) throws Exception;
    String deleteItem(DppEntity item) throws Exception;
}
