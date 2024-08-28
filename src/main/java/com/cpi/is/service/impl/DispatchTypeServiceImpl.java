package com.cpi.is.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.cpi.is.dao.impl.DispatchTypeDAOImpl;
import com.cpi.is.entity.DispatchTypeEntity;
import com.cpi.is.service.DispatchTypeService;

public class DispatchTypeServiceImpl implements DispatchTypeService {

	private DispatchTypeDAOImpl dispatchTypeDAO;
	
	public DispatchTypeDAOImpl getDispatchTypeDAO() {
		return dispatchTypeDAO;
	}

	public void setDispatchTypeDAO(DispatchTypeDAOImpl dispatchTypeDAO) {
		this.dispatchTypeDAO = dispatchTypeDAO;
	}
	
	private DispatchTypeEntity jsonToEntity(JSONObject json) {
		String dispatchTypeCode = json.getString("dispatchTypeCode");
		String dispatchTypeName = json.getString("dispatchTypeName");
		String isActive = json.getString("isActive");

		return new DispatchTypeEntity(dispatchTypeCode, dispatchTypeName, isActive);
	}

	@Override
	public List<DispatchTypeEntity> getDispatchType() throws Exception {
		return dispatchTypeDAO.getDispatchType();
	}

	@Override
	public String saveItem(HttpServletRequest request) throws Exception {
		return dispatchTypeDAO.saveItem(
				jsonToEntity(new JSONObject(request.getParameter("item"))));
	}

	@Override
	public String deleteItem(HttpServletRequest request) throws Exception {
		return dispatchTypeDAO.deleteItem(
				jsonToEntity(new JSONObject(request.getParameter("item"))));
	}

	
}
