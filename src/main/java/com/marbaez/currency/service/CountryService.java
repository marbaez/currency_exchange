package com.marbaez.currency.service;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.Country;

public interface CountryService {
	Country getCountryByAlpha3Code(String code) throws ExchangeServiceException;
}
