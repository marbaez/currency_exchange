package com.marbaez.currency;

import java.util.regex.Pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CurrencyExchangeApplication {

    @Bean
    public RestOperations restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Pattern alpha3codePattern() {
        final String patternString = "^[a-zA-ZñÑ]{3}$";
        final Pattern pattern = Pattern.compile(patternString);

        return pattern;
    }

    public static void main(final String[] args) {
        SpringApplication.run(CurrencyExchangeApplication.class, args);
    }
}
