package com.marbaez.currency.service;

import java.util.List;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.CurrencyChange;

public interface CurrencyService {

	List<CurrencyChange> getCurrencyExchange(String base, List<String> destinations) throws ExchangeServiceException;	
}
