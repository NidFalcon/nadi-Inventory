package com.cpi.is.dao.maintenance;

import java.util.List;

import com.cpi.is.entity.maintenance.SkuEntity;

public interface SkuDAO {
	
	List<SkuEntity> getSku() throws Exception;
	SkuEntity getSkuById(String skuCode) throws Exception;
	String saveItem(SkuEntity item) throws Exception;
	String deleteItem(SkuEntity item) throws Exception;
	
}
