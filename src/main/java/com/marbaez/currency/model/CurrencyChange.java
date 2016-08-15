package com.marbaez.currency.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Modelo que representa el recurso que vamos a devolver como respuesta a nuestra api,
 * con la moneda origen, la moneda destino y la tasa de cambio
 * @author marbaez
 *
 */
@JsonPropertyOrder({ "base", "destination", "rate" })
public class CurrencyChange {
	
	private final String originCurrency;
	
	private final String destinationCurrency;
	
	private final Double exchangeRate;


	public CurrencyChange(long id, String originCurrency, String destinationCurrency, Double exchangeRate) {
		this.originCurrency = originCurrency;
		this.destinationCurrency = destinationCurrency;
		this.exchangeRate = exchangeRate;
	}

	@JsonGetter("base_currency")
	protected String getOriginCurrency() {
		return originCurrency;
	}

	@JsonGetter("destination_currency")
	protected String getDestinationCurrency() {
		return destinationCurrency;
	}

	@JsonGetter("exchange_rate")
	protected Double getExchangeRate() {
		return exchangeRate;
	}
	
}
