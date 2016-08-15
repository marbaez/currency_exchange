package com.marbaez.currency.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "base", "destination" })
public class Greeting {
	private final String originCurrency;
	private final String destinationCurrency;

    public Greeting(String orig, String dest) {
        this.originCurrency = orig;
        this.destinationCurrency = dest;
    }

    @JsonGetter("base")
	protected String getOriginCurrency() {
		return originCurrency;
	}

    @JsonGetter("destination")
	protected String getDestinationCurrency() {
		return destinationCurrency;
	}
    
    

}
