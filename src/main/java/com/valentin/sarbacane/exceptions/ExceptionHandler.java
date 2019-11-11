package com.valentin.sarbacane.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	 @org.springframework.web.bind.annotation.ExceptionHandler(InvalidFileException.class)
	  public final ResponseEntity<ErrorResponse> handleInvalidFileException(InvalidFileException ex) {
		ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getCode());
	    return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	  }
	 
	 @org.springframework.web.bind.annotation.ExceptionHandler(EmptyFileException.class)
	 public final ResponseEntity<ErrorResponse> handleEmptyFileException(EmptyFileException ex) {
		 ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getCode());
		 return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	 }
	 
	 @org.springframework.web.bind.annotation.ExceptionHandler(InvalidColumnsException.class)
	 public final ResponseEntity<ErrorResponse> handleInvalidColumnsException(InvalidColumnsException ex) {
		 ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getCode());
		 return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	 }
}
