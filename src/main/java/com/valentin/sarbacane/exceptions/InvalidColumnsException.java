package com.valentin.sarbacane.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidColumnsException extends SarbacaneException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public InvalidColumnsException() {
		super("INVALID_COLUMNS", "422");
	}
	
	public InvalidColumnsException(String message, String code) {
		super(message, code);
	}
}
