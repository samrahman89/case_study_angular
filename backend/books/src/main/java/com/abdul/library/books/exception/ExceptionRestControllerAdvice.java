package com.abdul.library.books.exception;

import java.time.Instant;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestControllerAdvice {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionResponseMessage handleResourceNotFoundException(RuntimeException ex) {
		return sendResponse(HttpStatus.BAD_REQUEST, ex);
	}

	private ExceptionResponseMessage sendResponse(HttpStatus status, RuntimeException ex) {

		return new ExceptionResponseMessage(Instant.now(), status.value(), ex.getMessage());
	}
}
