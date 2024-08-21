package com.cpi.is.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String password;
	
	public UserEntity() {
		super();
	}

	public UserEntity(Integer userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
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
	
}
