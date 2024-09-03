package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.SkuEntity;

public interface SkuDAO {
	
	List<SkuEntity> getSku() throws Exception;
	String saveItem(SkuEntity item) throws Exception;
	String deleteItem(SkuEntity item) throws Exception;
	
}
