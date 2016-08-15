package com.marbaez.currency.error;

public class ValidationException extends RuntimeException {
	
	public ValidationException(String message) {
		super(message);
	}
}