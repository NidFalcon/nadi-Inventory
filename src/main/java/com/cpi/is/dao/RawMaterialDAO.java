package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.RawMaterialEntity;

public interface RawMaterialDAO {
	List<RawMaterialEntity> getRawMaterial() throws Exception;
	String saveItem(RawMaterialEntity item) throws Exception;
	String deleteItem(RawMaterialEntity item) throws Exception;

}
