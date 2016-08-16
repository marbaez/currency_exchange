package com.marbaez.currency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class CurrencyExchangeApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String wrongUrl = "/notFound";

    @Test
    public void notFoundURL() {

        final String body = this.restTemplate.getForObject(wrongUrl, String.class);
        assertThat(body).contains("404");
        assertThat(body).contains("Not Found");
    }

}
