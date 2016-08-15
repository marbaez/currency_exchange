package com.marbaez.currency.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Clase para mapear la respuesta de la API
 * @author marbaez
 *
 */
public class CurrencyChangeFixer {

	/**
	 * Moneda origen
	 */
	private String base;
	
	/**
	 * Representa una lista de pares <moneda,tipo de cambio>
	 */
	private Map<String,Double> rates;
	
	
	public CurrencyChangeFixer(String base, Map<String, Double> rates) {
		super();
		this.base = base;
		this.rates = rates;
	}

	public CurrencyChangeFixer() {
		super();
	}

	protected String getBase() {
		return base;
	}

	protected void setBase(String base) {
		this.base = base;
	}

	protected Map<String, Double> getRates() {
		return rates;
	}

	protected void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "CurrencyChangeFixer [base=" + base + ", rates=" + rates + "]";
	}
	
	public List<CurrencyChange> convertToCurrencyChange() {
		List<CurrencyChange> result = new ArrayList<CurrencyChange>();
		
		for (String currency : rates.keySet()) {
			result.add( new CurrencyChange(1, this.base, currency, rates.get(currency)));
		}
		return result;
	}
	
}
