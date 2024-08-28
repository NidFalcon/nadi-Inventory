package com.cpi.is.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="QKC_BRANCH")
public class BranchEntity {

	@Id
	@Column(name="BRANCH_ID")
	private Integer branchId;
	@Column(name="BRANCH_NAME")
	private String branchName;
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	public BranchEntity() {
		super();
	}

	public BranchEntity(Integer branchId, String branchName, String isActive) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.isActive = isActive;
	}
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
}