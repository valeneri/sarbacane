package com.valentin.sarbacane.exceptions;

public class SarbacaneException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String code;
	
	/**
	 * 
	 */
	public SarbacaneException() {
		super();
	}
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SarbacaneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	/**
	 * @param message
	 * @param cause
	 */
	public SarbacaneException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * @param message
	 */
	public SarbacaneException(String message) {
		super(message);
	}
	/**
	 * @param cause
	 */
	public SarbacaneException(Throwable cause) {
		super(cause);
	}
	/**
	 * @param message
	 * @param code
	 */
	public SarbacaneException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
