package com.marbaez.currency.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8902379711976302062L;

	public ValidationException(String message) {
		super(message);
	}
}