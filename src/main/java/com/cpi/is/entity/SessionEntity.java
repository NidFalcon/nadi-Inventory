package com.cpi.is.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="QKC_SESSIONS")
public class SessionEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SESSION_ID")
	private String sessionId;
	private String username;
	
	public SessionEntity() {
		super();
	}

	public SessionEntity(String sessionId, String username) {
		super();
		this.sessionId = sessionId;
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "{sessionId=" + sessionId 
			 + ", username=" + username + "}";
	}
	
}