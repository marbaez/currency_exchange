package com.marbaez.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.CurrencyChange;
import com.marbaez.currency.model.CurrencyChangeFixer;
import com.marbaez.currency.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	
	@Value("${exchange.api.url}")
	private String currenciesExchangeApiURL;
	
	protected void setCurrenciesExchangeApiURL(String currenciesExchangeApiURL) {
		this.currenciesExchangeApiURL = currenciesExchangeApiURL;
	}

	@Override
	public List<CurrencyChange> getCurrencyExchange(String base, List<String> destinations) throws ExchangeServiceException {
		try {
			RestTemplate rest = new RestTemplate();
			CurrencyChangeFixer currencyChange = rest.getForObject(currenciesExchangeApiURL,
					CurrencyChangeFixer.class, base, destinations);
			return currencyChange.convertToCurrencyChange();
			
		} catch (RestClientException rce) {
			throw new ExchangeServiceException("Currency API could not get requested info about conversions between " + base + " and destinations currencies: " + destinations);
		}
	}

}
