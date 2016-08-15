package com.marbaez.currency.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marbaez.currency.error.ExchangeServiceException;
import com.marbaez.currency.model.Country;
import com.marbaez.currency.model.CurrencyChange;
import com.marbaez.currency.service.CountryService;
import com.marbaez.currency.service.CurrenciesRatesService;
import com.marbaez.currency.service.CurrencyService;

/**
 * Implementación del servicio definido en el interfaz CurrenciesRatesService
 * que obtiene los datos de las conversiones de monedas de dos apis externas en json 
 * utilizando para ello RestTemplate's de spring-web
 * @author marbaez
 *
 */
@Service
public class CurrencyRatesServiceImpl implements CurrenciesRatesService {
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CountryService countryService;

	protected void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	protected void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	/**
	 * Devuelve la lista de los cambios respecto de la moneda principal del país indicado como 'originCountry'
	 * con respecto a los parises indicados en la lista 'destinationCountries'
	 */
	public List<CurrencyChange> currenciesChangesForBaseCountry(String originCountry, String... destinationCountries) throws ExchangeServiceException {
		if (destinationCountries.length > 0) {
			
			Country origin = getCountryData(originCountry);
			List<Country> destCountries = getCountriesData(destinationCountries);
			return getCurrencyExchange(origin, destCountries);
		} else {
			return new ArrayList<CurrencyChange>();
		}
	}

	/**
	 * Obtiene los datos de los países indicados en la lista por su código de 3 caracteres
	 * @param destinationCountries
	 * @return
	 */
	private List<Country> getCountriesData(String... destinationCountries) throws ExchangeServiceException {
		List<Country> destCountries = new  ArrayList<Country>();
		for (String countryCode : destinationCountries) {
			destCountries.add(getCountryData(countryCode));
		}
		return destCountries;
	}
	
	/**
	 * Obtiene los datos de un país dado realizando una llamada al servicio correspondiente y manejando su respuesta.
	 * @param countryCode
	 * @return
	 * @throws NotFoundCountryException
	 */
	private Country getCountryData(String countryCode) throws ExchangeServiceException {
		return countryService.getCountryByAlpha3Code(countryCode);		
	}
	
	/**
	 * Realiza una llamada al servicio de cambio de moneda para obetener la base de cambio de la moneda del país principal 
	 * 'origin' respecto a las monedas de los países que se indican en la lista 'destination'
	 * @param origin
	 * @param destinations
	 * @return
	 * @throws CurrencyChangeException
	 */
	private List<CurrencyChange> getCurrencyExchange(Country origin, List<Country> destinations) throws ExchangeServiceException {
		String originCurrency = origin.getFirstCurrency();
		List<String> destCurencies = getCurrenciesList(destinations);
		
		return currencyService.getCurrencyExchange(originCurrency, destCurencies);
	}

	/**
	 * Obtiene la lista de los códigos de las monedas de los países que vienen en el parámetro destination.
	 * @param destinations
	 * @return
	 */
	private List<String> getCurrenciesList(List<Country> destinations) {
		List<String> result = new ArrayList<>();
		
		for (Country country : destinations) {
				result.add(country.getFirstCurrency());
		}
		
		return result;
	}
}
