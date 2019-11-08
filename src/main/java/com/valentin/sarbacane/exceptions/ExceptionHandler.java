package com.valentin.sarbacane.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	 @ExceptionHandler(InvalidFileException.class)
	  public final ResponseEntity<SarbacaneException> handleInvalidFileException(InvalidFileException ex) {
		 
		SarbacaneException exception = new SarbacaneException();
		exception.setMessage("INVALID_FILE_FORMAT");
		exception.setCode("415");
	    return new ResponseEntity(exception, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	  }
	 
	 @ExceptionHandler(NoFileException.class)
	 public final ResponseEntity<SarbacaneException> handleNoFileExcpetion(NoFileException ex) {
		 SarbacaneException exception = new SarbacaneException();
		 exception.setMessage("NO_FILE_PROVIDED");
		 exception.setCode("400");
		 return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
	 }
}
