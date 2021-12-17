package com.alex.sisos.controller.exceptions;

import java.io.Serializable;

public class FildMessage implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;
	public FildMessage() {
		super();
	}
	
	public FildMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
