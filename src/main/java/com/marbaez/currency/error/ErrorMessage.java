package com.marbaez.currency.error;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	private String description;
	private HttpStatus status;
	
	public ErrorMessage(String description, HttpStatus status) {
		super();
		this.description = description;
		this.status = status;
	}
	protected String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
	protected HttpStatus getStatus() {
		return status;
	}
	protected void setStatus(HttpStatus status) {
		this.status = status;
	}
}
