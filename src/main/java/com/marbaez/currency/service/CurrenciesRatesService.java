package com.marbaez.currency.service;

import java.util.List;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.CurrencyChange;


/**
 * Interfaz que representa el servicio que se va a utilizar para obtener las tasas de conversión de una moneda origen a una o varias destino
 * @author marbaez
 *
 */
public interface CurrenciesRatesService {
	List<CurrencyChange> currenciesChangesForBaseCountry(String originCountry, String...destinationCountries) throws ExchangeServiceException;
}
