package com.cpi.is.dao;

import java.util.List;
import com.cpi.is.entity.DppEntity;

public interface DppDAO {
    
    List<DppEntity> getDpp() throws Exception;
    String saveItem(DppEntity item) throws Exception;
    String deleteItem(DppEntity item) throws Exception;
}
