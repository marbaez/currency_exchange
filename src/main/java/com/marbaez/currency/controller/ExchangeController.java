package com.marbaez.currency.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.error.ValidationException;
import com.marbaez.currency.model.CurrencyChange;
import com.marbaez.currency.service.CurrenciesRatesService;

@RestController
public class ExchangeController {

	private static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

	@Autowired
	private CurrenciesRatesService currenciesRatesService;	
	
	@RequestMapping(
			value = "/multiple",
			method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CurrencyChange>> exchange(
			@RequestParam(value="base", defaultValue="ESP", required = false) String base, 
			@RequestParam(value = "destination", required = false) String[] destinations) throws ExchangeServiceException, ValidationException {
		
		//validación de los parámetros de entrada
		validateExchangeRequestParameters(base, destinations);
		
		return new ResponseEntity<List<CurrencyChange>>(
				currenciesRatesService.currenciesChangesForBaseCountry(base, destinations),
				HttpStatus.OK);
	}
	
	private void validateExchangeRequestParameters(String base, String[] destinations) throws ValidationException {
		
		//validamos el cód. del país de la moneda de origen
		validateAlpha3CountryCode(base);
		
		if (destinations == null || destinations.length < 1) {
			throw new ValidationException("Se debe indicar al menos un código de país destino");
		} else {
			for (String code: destinations) {
				validateAlpha3CountryCode(code);
			}
		}
		
	}
	
	
	/**
	 * Validamos que el parámetro de entrada sea una cadena de caracteres de longitud 3
	 * @param code
	 * @throws ValidationException
	 */
	private void validateAlpha3CountryCode(String code) throws ValidationException {
		String patternString = "^[a-zA-ZñÑ]{3}$";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(code);
		
		if (!matcher.matches()) {
			throw new ValidationException("[" + code + "] no es un código de país válido.");
		}
	}
	
}
