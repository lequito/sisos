package com.alex.sisos.controller.exceptions;

import java.io.Serializable;

public class StandardErros implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer status;
	private String error;
	public StandardErros() {
		super();
	}

	public StandardErros(Long timestamp, Integer status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	
}
