package com.cpi.is.service.impl;

import java.util.List;

import com.cpi.is.dao.impl.RawMaterialDAOImpl;
import com.cpi.is.entity.RawMaterialEntity;

public class RawMaterialServiceImpl {
	private RawMaterialDAOImpl rawMaterialDAO;
	
	public RawMaterialServiceImpl() {
		super();
	}

	public RawMaterialServiceImpl(RawMaterialDAOImpl rawMaterialDAO) {
		super();
		this.rawMaterialDAO = rawMaterialDAO;
	}

	public RawMaterialDAOImpl getRawMaterialDAO() {
		return rawMaterialDAO;
	}

	public void setRawMaterialDAO(RawMaterialDAOImpl rawMaterialDAO) {
		this.rawMaterialDAO = rawMaterialDAO;
	}
	
	public List<RawMaterialEntity> getAllRawMaterials() {
		return rawMaterialDAO.getAllRawMaterials();
	}
}
