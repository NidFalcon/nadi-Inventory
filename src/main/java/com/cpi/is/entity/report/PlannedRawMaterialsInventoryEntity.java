package com.cpi.is.entity.report;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PlannedRawMaterialsInventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MATERIAL_CD")
	private String materialCd;
	@Column(name="MATERIAL_NAME")
	private String materialName;
	private Long quantity;

	public PlannedRawMaterialsInventoryEntity() {
		super();
	}

	public PlannedRawMaterialsInventoryEntity(String materialCd, String materialName, Long quantity) {
		super();
		this.materialCd = materialCd;
		this.materialName = materialName;
		this.quantity = quantity;
	}

	public String getMaterialCd() {
		return materialCd;
	}

	public void setMaterialCd(String materialCd) {
		this.materialCd = materialCd;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "{ materialCd=" + materialCd 
			 + ", materialName=" + materialName
			 + ", quantity=" + quantity + " }";
	}

}