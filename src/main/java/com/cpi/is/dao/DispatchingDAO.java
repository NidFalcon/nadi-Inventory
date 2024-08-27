package com.cpi.is.dao;

import java.util.List;
import com.cpi.is.entity.DispatchingEntity;

public interface DispatchingDAO {
	
	List<DispatchingEntity> getDispatching() throws Exception;
	String saveItem(DispatchingEntity item) throws Exception;
	String deleteItem(DispatchingEntity item) throws Exception;
	
}
