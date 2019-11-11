package com.valentin.sarbacane.exceptions;

public class EmptyFileException extends SarbacaneException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmptyFileException() {
		super("EMPTY_FILE", "400");
	}
	/**
	 * 
	 * @param message
	 * @param code
	 */
	public EmptyFileException(String message, String code) {
		super(message, code);
	}

}
