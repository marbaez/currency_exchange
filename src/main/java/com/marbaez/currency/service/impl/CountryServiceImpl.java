package com.marbaez.currency.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.Country;
import com.marbaez.currency.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Value("${countries.api.url}")
	private String countriesApiURL;
	
	protected void setCountriesApiURL(String countriesApiURL) {
		this.countriesApiURL = countriesApiURL;
	}
	
	@Override
	public Country getCountryByAlpha3Code(String code) throws ExchangeServiceException {
		RestTemplate rest = new RestTemplate();
		try {
			Country country = rest.getForObject(countriesApiURL,
				     Country.class, code);
			return country;
		} catch (RestClientException rce) {
			throw new ExchangeServiceException("Country API could not found info about requested country code -> " + code);
		}
	}

}
