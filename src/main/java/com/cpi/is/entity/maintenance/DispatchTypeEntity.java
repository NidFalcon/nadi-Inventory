package com.cpi.is.entity.maintenance;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="QKC_DISPATCH_TYPE")
public class DispatchTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DISPATCH_TYPE_CD")
	private String dispatchTypeCode;

	@Column(name="DISPATCH_TYPE_NAME")
	private String dispatchTypeName;

	@Column(name="IS_ACTIVE")
	private String isActive;

	public DispatchTypeEntity() {
		super();
	}

	public DispatchTypeEntity(String dispatchTypeCode, String dispatchTypeName, String isActive) {
		super();
		this.dispatchTypeCode = dispatchTypeCode;
		this.dispatchTypeName = dispatchTypeName;
		this.isActive = isActive;
	}

	public String getDispatchTypeCode() {
		return dispatchTypeCode;
	}

	public void setDispatchTypeCode(String dispatchTypeCode) {
		this.dispatchTypeCode = dispatchTypeCode;
	}

	public String getDispatchTypeName() {
		return dispatchTypeName;
	}

	public void setDispatchTypeName(String dispatchTypeName) {
		this.dispatchTypeName = dispatchTypeName;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DispatchTypeEntity{" +
				"dispatchTypeCode='" + dispatchTypeCode + '\'' +
				", dispatchTypeName='" + dispatchTypeName + '\'' +
				", isActive='" + isActive + '\'' +
				'}';
	}
}
