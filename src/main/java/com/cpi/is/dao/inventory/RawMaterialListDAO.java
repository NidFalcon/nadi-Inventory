package com.cpi.is.dao.inventory;

import java.util.List;

import com.cpi.is.entity.inventory.RawMaterialListEntity;

public interface RawMaterialListDAO {
	
	List<RawMaterialListEntity>  getRawMaterialList(Long targetBranchId) throws Exception;
	String saveRawMaterial(RawMaterialListEntity item) throws Exception;
	String deleteRawMaterial(RawMaterialListEntity item) throws Exception;
}
