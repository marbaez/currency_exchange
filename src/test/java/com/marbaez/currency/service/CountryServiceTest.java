package com.marbaez.currency.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceTest {

    @MockBean
    private RestOperations rest;

    @Autowired
    private CountryService countryService;

    @Test
    public void testGetCorrectCountryByAlpha3Code() {
        final String countryCode = "ESP";
        final String[] curreciesList = { "EUR" };
        given(rest.getForObject(anyString(),
                eq(Country.class),
                eq(countryCode)))
                        .willReturn(
                                new Country("ESP", Arrays.asList(curreciesList)));

        final Country country = countryService.getCountryByAlpha3Code(countryCode);

        assertThat(country.getFirstCurrency()).isEqualTo("EUR");
    }

    @Test(
            expected = ExchangeServiceException.class)
    public void testInvalidAlpha3Code() {
        final String countryCode = "AAA";

        given(rest.getForObject(anyString(),
                eq(Country.class),
                eq(countryCode)))
                        .willThrow(new RestClientException(""));

        final Country country = countryService.getCountryByAlpha3Code(countryCode);
    }

}
