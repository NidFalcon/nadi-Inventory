package com.cpi.is.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="QKC_USER")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USER_ID")
	private Integer userId;
	private String username;
	private String password;
	@Column(name="BRANCH_ID")
	private Integer branchId;
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	public UserEntity() {
		super();
	}

	public UserEntity(Integer userId, String username, String password, Integer branchId, Boolean isActive) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.branchId = branchId;
		this.isActive = isActive;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "{userId=" + userId 
			 + ", username=" + username 
			 + ", password=" + password + "}";
	}
	
}
