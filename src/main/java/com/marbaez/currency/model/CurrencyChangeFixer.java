package com.marbaez.currency.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private Map<String, Double> rates;

    public CurrencyChangeFixer(final String base, final Map<String, Double> rates) {
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

    protected void setBase(final String base) {
        this.base = base;
    }

    protected Map<String, Double> getRates() {
        return rates;
    }

    protected void setRates(final Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyChangeFixer [base=" + base + ", rates=" + rates + "]";
    }

    public List<CurrencyChange> convertToCurrencyChange() {
        final List<CurrencyChange> result = new ArrayList<CurrencyChange>();

        for (final String currency : rates.keySet()) {
            result.add(new CurrencyChange(this.base, currency, rates.get(currency)));
        }
        return result;
    }

}
