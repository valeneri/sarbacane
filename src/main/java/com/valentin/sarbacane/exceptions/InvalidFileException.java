package com.valentin.sarbacane.exceptions;

public class InvalidFileException extends SarbacaneException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidFileException() {
		super("INVALID_FILE_FORMAT", "415");
	}
	/**
	 * 
	 * @param message
	 * @param code
	 */
	public InvalidFileException(String message, String code) {
		super(message, code);
	}
}
