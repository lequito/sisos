package com.alex.sisos.services.excepitions;

public class DataIntegratyViolationExcepition extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataIntegratyViolationExcepition(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegratyViolationExcepition(String message) {
		super(message);
	}


}
