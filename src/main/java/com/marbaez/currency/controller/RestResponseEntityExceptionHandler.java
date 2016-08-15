package com.marbaez.currency.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.marbaez.currency.error.ErrorMessage;
import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.error.NotFoundResourceException;
import com.marbaez.currency.error.ValidationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { NotFoundResourceException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ResponseEntity<Object> handleNotFoundResourceException(NotFoundResourceException ex, WebRequest request) {
    	ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
    	//return new ResponseEntity<Object>(errorMessage, HttpStatus.NOT_FOUND);
    	return handleExceptionInternal(ex, errorMessage, 
    	          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        
    }
    
    @ExceptionHandler(value = { ExchangeServiceException.class })
    protected ResponseEntity<Object> handleConflictCurrencyExchange(ExchangeServiceException ex, WebRequest request) {
    	ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return handleExceptionInternal(ex, errorMessage, 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    @ExceptionHandler(value = { ValidationException.class })
    protected ResponseEntity<Object> handleConflictCurrencyExchange(ValidationException ex, WebRequest request) {
    	ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(ex, errorMessage, 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
