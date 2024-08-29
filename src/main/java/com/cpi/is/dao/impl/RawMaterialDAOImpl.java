package com.cpi.is.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.cpi.is.dao.RawMaterialDAO;
import com.cpi.is.entity.RawMaterialEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.util.HBUtil;

public class RawMaterialDAOImpl implements RawMaterialDAO {

	@Override
	public RawMaterialEntity getRawMaterial(String materialCode) {
		RawMaterialEntity foundMaterial = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			List<RawMaterialEntity> results = (List<RawMaterialEntity>) session
					.createQuery("FROM RawMaterialEntity M WHERE M.materialCode = :materialCode", RawMaterialEntity.class)
					.setParameter("materialCode", materialCode)
					.list();
			if (results.size() > 0) {
				foundMaterial = results.get(0);
			}
			
			return foundMaterial;
		}
	}

}
