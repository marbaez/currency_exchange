package com.marbaez.currency.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.CurrencyChange;
import com.marbaez.currency.model.CurrencyChangeFixer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyServiceTest {

    @MockBean
    private RestOperations rest;

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void testValidCurrencyCodeSingleConversion() {
        final String baseCurrencyCode = "USD";
        final String[] destinationCurrenciesCodes = { "EUR" };
        final Map<String, Double> restResult = new HashMap<>();
        restResult.put(destinationCurrenciesCodes[0], 1.1);
        final List<CurrencyChange> expectedResults = new ArrayList<>();
        expectedResults.add(new CurrencyChange(baseCurrencyCode, destinationCurrenciesCodes[0], 1.1));

        given(rest.getForObject(anyString(),
                eq(CurrencyChangeFixer.class),
                eq(baseCurrencyCode),
                eq(Arrays.asList(destinationCurrenciesCodes))))
                        .willReturn(new CurrencyChangeFixer(baseCurrencyCode, restResult));

        final List<CurrencyChange> results = currencyService.getCurrencyExchange(baseCurrencyCode,
                Arrays.asList(destinationCurrenciesCodes));

        assertThat(results.size()).isEqualTo(1);
        assertThat(results.get(0).getOriginCurrency()).isEqualTo("USD");
        assertThat(results.get(0).getDestinationCurrency()).isEqualTo("EUR");
        assertThat(results.get(0).getExchangeRate()).isEqualTo(1.1);
    }

    @Test(
            expected = ExchangeServiceException.class)
    public void testInvalidCurrencyCodeExchange() {
        final String baseCurrencyCode = "AAA";
        final String[] destinationCurrenciesCodes = { "EUR" };
        final Map<String, Double> restResult = new HashMap<>();
        restResult.put(destinationCurrenciesCodes[0], 1.1);
        final List<CurrencyChange> expectedResults = new ArrayList<>();
        expectedResults.add(new CurrencyChange(baseCurrencyCode, destinationCurrenciesCodes[0], 1.1));

        given(rest.getForObject(anyString(),
                eq(CurrencyChangeFixer.class),
                eq(baseCurrencyCode),
                eq(Arrays.asList(destinationCurrenciesCodes))))
                        .willThrow(new RestClientException(""));

        final List<CurrencyChange> results = currencyService.getCurrencyExchange(baseCurrencyCode,
                Arrays.asList(destinationCurrenciesCodes));

    }

}
