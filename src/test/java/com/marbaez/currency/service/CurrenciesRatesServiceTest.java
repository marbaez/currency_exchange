package com.marbaez.currency.service;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrenciesRatesServiceTest {

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private CountryService countryService;

    @Autowired
    private CurrenciesRatesService currenciesRatesService;

    @Test
    @Ignore
    public void test() {
        fail("Not yet implemented");
    }

}
