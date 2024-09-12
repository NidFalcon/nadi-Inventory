package com.cpi.is.dao.maintenance;

import java.util.List;

import com.cpi.is.entity.maintenance.RawMaterialEntity;

public interface RawMaterialDAO {
	List<RawMaterialEntity> getRawMaterial() throws Exception;
	RawMaterialEntity getRawMaterialById(String materialCode) throws Exception;
	String saveItem(RawMaterialEntity item) throws Exception;
	String deleteItem(RawMaterialEntity item) throws Exception;
}
