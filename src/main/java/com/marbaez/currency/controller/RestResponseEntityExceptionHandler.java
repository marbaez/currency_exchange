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
    	return handleError(ex, HttpStatus.NOT_FOUND, request);
        
    }
    
    @ExceptionHandler(value = { ExchangeServiceException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected @ResponseBody ResponseEntity<Object> handleConflictCurrencyExchange(ExchangeServiceException ex, WebRequest request) {
        return handleError(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    @ExceptionHandler(value = { ValidationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected @ResponseBody ResponseEntity<Object> handleConflictCurrencyExchange(ValidationException ex, WebRequest request) {
    	return handleError(ex, HttpStatus.BAD_REQUEST, request);
    }
    
    private ResponseEntity<Object> handleError(Exception ex, HttpStatus status, WebRequest request) {
    	ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(ex, errorMessage, 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
