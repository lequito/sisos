package com.alex.sisos.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardErros{

	private static final long serialVersionUID = 1L;

	private List<FildMessage> erros = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FildMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		this.erros.add(new FildMessage(fieldName, message));
	}
	
	
	
	
}
