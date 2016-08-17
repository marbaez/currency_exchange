package com.marbaez.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.CurrencyChange;
import com.marbaez.currency.model.CurrencyChangeFixer;
import com.marbaez.currency.service.CurrencyService;
import com.marbaez.currency.util.Messages;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${exchange.api.url}")
    private String currenciesExchangeApiURL;

    @Autowired
    private RestOperations rest;
    
    @Autowired
    private Messages messages;

    protected void setCurrenciesExchangeApiURL(final String currenciesExchangeApiURL) {
        this.currenciesExchangeApiURL = currenciesExchangeApiURL;
    }

    @Override
    public List<CurrencyChange> getCurrencyExchange(final String base, final List<String> destinations)
            throws ExchangeServiceException {
        try {
            final CurrencyChangeFixer currencyChange = rest.getForObject(currenciesExchangeApiURL,
                    CurrencyChangeFixer.class, base, destinations);
            return currencyChange.convertToCurrencyChange();

        } catch (final RestClientException rce) {
            throw new ExchangeServiceException(messages.getString("currency.api.error", base, destinations));
        } catch (final Exception ex) {
            throw new ExchangeServiceException(messages.getString("currency.api.unknow.error"));
        }
    }

}
