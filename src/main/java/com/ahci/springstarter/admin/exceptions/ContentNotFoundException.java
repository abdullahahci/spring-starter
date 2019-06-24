package com.ahci.springstarter.admin.exceptions;

public class ContentNotFoundException extends IllegalArgumentException {
	
	private static final long serialVersionUID = -389100779526868733L;
	
	private String message;
	
	public ContentNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public ContentNotFoundException(String message) {
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
