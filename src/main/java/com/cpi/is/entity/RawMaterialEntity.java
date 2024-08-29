package com.cpi.is.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="qkc_raw_material")
public class RawMaterialEntity {
	
	@Id
	@Column(name="MATERIAL_CD")
	private String materialCode;
	@Column(name="MATERIAL_NAME")
	private String materialName;
	@Column(name="UNIT_OF_MEASUREMENT")
	private String unitOfMeasurement;
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	public RawMaterialEntity() {
		super();
	}
	public RawMaterialEntity(String materialCode, String materialName, String unitOfMeasurement, String isActive) {
		super();
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.unitOfMeasurement = unitOfMeasurement;
		this.isActive = isActive;
	}
	
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
