package com.marbaez.currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.marbaez.currency.model.CurrencyChange;
import com.marbaez.currency.service.CurrenciesRatesService;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class CurrencyExchangeApplicationTests {
	
	@MockBean
	private CurrenciesRatesService currenciesRateService;
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void notFoundURL() {
    	String wrongUrl = "/notExistingURL";
    	
        final String body = this.restTemplate.getForObject(wrongUrl, String.class);
        assertThat(body).contains("404");
        assertThat(body).contains("Not Found");
    }
    
    @Test
    public void exchangeInvalidBaseParameter() {
    	final String exchangeURL = "/exchanges?base=ABCD&destination=USA";
    	
    	final String body = this.restTemplate.getForObject(exchangeURL, String.class);
        assertThat(body).contains("400");
        assertThat(body).contains("Bad Request");
    }
    
    @Test
    public void exchangeEmptyDestinationParams() {
    	final String exchangeURL = "/exchanges?base=USA";
    	
    	final String body = this.restTemplate.getForObject(exchangeURL, String.class);
        assertThat(body).contains("400");
        assertThat(body).contains("Bad Request");
    }

    @Test
    public void testExchangeRequestOK() {
    	String origin = "USA";
    	String[] listDestinations = {"ESP"};
    	CurrencyChange[] change = {new CurrencyChange("USD", "EUR", 0.811)};
    	
        given(currenciesRateService.currenciesChangesForBaseCountry(origin, listDestinations))
                .willReturn(Arrays.asList(change));
        
        final String exchangeURL = "/exchanges?base=USA&destination=ESP";
    	
    	final String body = this.restTemplate.getForObject(exchangeURL, String.class);
        assertThat(body).contains("base_currency");
        assertThat(body).contains("USD");
        assertThat(body).contains("destination_currency");
        assertThat(body).contains("EUR");
    }
}
