package com.marbaez.currency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.Country;
import com.marbaez.currency.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    @Value("${countries.api.url}")
    private String countriesApiURL;

    @Autowired
    private RestOperations rest;

    protected void setCountriesApiURL(final String countriesApiURL) {
        this.countriesApiURL = countriesApiURL;
    }

    @Override
    public Country getCountryByAlpha3Code(final String code) throws ExchangeServiceException {
        try {
            final Country country = rest.getForObject(countriesApiURL,
                    Country.class, code);
            return country;
        } catch (final RestClientException rce) {
            throw new ExchangeServiceException(
                    "Country API could not found info about requested country code -> " + code);
        }
    }

}
