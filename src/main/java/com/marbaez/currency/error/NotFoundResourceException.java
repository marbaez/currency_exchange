package com.marbaez.currency.error;

public class NotFoundResourceException extends RuntimeException {
	public NotFoundResourceException(String message) {
		super(message);
	}
}