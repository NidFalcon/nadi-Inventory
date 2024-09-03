package com.cpi.is.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="qkc_raw_material")
public class RawMaterialListEntity {
	
	@Id
	@Column(name="MATERIAL_LIST_ID")
	private String materialListId;
	@Column(name="MATERIAL_CD")
	private String materialCode;
	private String quantity;
	@Column(name="USER_ID")
	private String userId;
	@Column(name="DATE_RECIEVE")
	private String dateRecieve;
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	public RawMaterialListEntity() {
		super();
	}
	
	public RawMaterialListEntity(String materialListId, String materialCode, String quantity, String userId,
			String dateRecieve, String isActive) {
		super();
		this.materialListId = materialListId;
		this.materialCode = materialCode;
		this.quantity = quantity;
		this.userId = userId;
		this.dateRecieve = dateRecieve;
		this.isActive = isActive;
	}
	public String getMaterialListId() {
		return materialListId;
	}
	public void setMaterialListId(String materialListId) {
		this.materialListId = materialListId;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDateRecieve() {
		return dateRecieve;
	}
	public void setDateRecieve(String dateRecieve) {
		this.dateRecieve = dateRecieve;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
}
