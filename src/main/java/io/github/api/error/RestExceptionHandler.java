package io.github.api.error;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.NotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> handleGenericException(Exception e) {
		return new ResponseEntity<> (new ResponseMessage(e.getMessage(), HttpStatus.BAD_REQUEST.toString()),
				HttpStatus.BAD_REQUEST);
	}
	
	// Response 404
	@ExceptionHandler({NotFoundException.class, EmptyResultDataAccessException.class})
	public ResponseEntity<ResponseMessage> handleNotFoundException(Exception e) {
		return new ResponseEntity<> (new ResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND.toString()),
				HttpStatus.NOT_FOUND);
	}
}
