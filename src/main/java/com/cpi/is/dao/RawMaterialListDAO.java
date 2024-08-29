package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.RawMaterialListEntity;

public interface RawMaterialListDAO {
	
	List<Object[]> getRawMaterialList() throws Exception;
	String saveItem(RawMaterialListEntity item) throws Exception;
	String deleteItem(RawMaterialListEntity item) throws Exception;
}
