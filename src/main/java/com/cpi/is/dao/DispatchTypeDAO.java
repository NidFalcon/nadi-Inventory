package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.DispatchTypeEntity;

public interface DispatchTypeDAO {
	
	List<DispatchTypeEntity> getDispatchType() throws Exception;
	DispatchTypeEntity getDispatchTypeById(String dispatchTypeCode) throws Exception;
	String saveItem(DispatchTypeEntity item) throws Exception;
	String deleteItem(DispatchTypeEntity item) throws Exception;
	
}
