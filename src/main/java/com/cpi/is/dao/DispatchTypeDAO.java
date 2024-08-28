package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.DispatchTypeEntity;

public interface DispatchTypeDAO {
	
	List<DispatchTypeEntity> getDispatchTypes() throws Exception;
	String saveItem(DispatchTypeEntity dispatchType) throws Exception;
	String deleteItem(DispatchTypeEntity dispatchType) throws Exception;
	
}
