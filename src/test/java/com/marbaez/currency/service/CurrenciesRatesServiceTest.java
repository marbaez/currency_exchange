package com.marbaez.currency.service;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.Country;
import com.marbaez.currency.model.CurrencyChange;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrenciesRatesServiceTest {

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private CountryService countryService;

    @Autowired
    private CurrenciesRatesService currenciesRatesService;

    private final String goodOriginCountry = "ESP";
    final String[] originCountryCurreciesList = { "EUR" };

    private final String[] goodDestinationCountry = { "USA" };
    final String[] destinationCountryCurreciesList = { "USD" };

    private final String wrongCountry = "XXX";

    @Before
    public void setUp() throws Exception {
        //Mocking countriesService
        given(countryService.getCountryByAlpha3Code(goodOriginCountry))
                .willReturn(new Country(goodOriginCountry, Arrays.asList(originCountryCurreciesList)));

        given(countryService.getCountryByAlpha3Code(goodDestinationCountry[0]))
                .willReturn(new Country(goodDestinationCountry[0], Arrays.asList(destinationCountryCurreciesList)));

        given(countryService.getCountryByAlpha3Code(wrongCountry))
                .willThrow(new ExchangeServiceException(
                        "Country API could not found info about requested country code -> " + wrongCountry));

        //Mocking currencyService
        //List<CurrencyChange> getCurrencyExchange(String base, List<String> destinations) throws ExchangeServiceException;
        final List<CurrencyChange> exchangeResults = new ArrayList<>();
        exchangeResults.add(new CurrencyChange("EUR", "USD", 1.1));

        given(currencyService.getCurrencyExchange(originCountryCurreciesList[0],
                Arrays.asList(destinationCountryCurreciesList)))
                        .willReturn(exchangeResults);
    }

    @Test
    public void testSucessfullCurrencyExchange() {
        final List<CurrencyChange> result = currenciesRatesService.currenciesChangesForBaseCountry(goodOriginCountry,
                goodDestinationCountry);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getOriginCurrency()).isEqualTo(originCountryCurreciesList[0]);
        assertThat(result.get(0).getDestinationCurrency()).isEqualTo(destinationCountryCurreciesList[0]);
    }

}
