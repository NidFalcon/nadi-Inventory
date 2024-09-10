package com.cpi.is.exception;

public class InvalidJsonException extends Exception {

	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidJsonException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
