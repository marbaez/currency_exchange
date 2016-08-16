package com.marbaez.currency.model;

import java.util.List;

/**
 * Clase que representa el modelo par aun país que vamos a manejar.
 * De momento tan sólo necesitamos su código de país y los códigos de las monedas que se manejan en él.
 * @author marbaez
 *
 */
public class Country {

    private String alpha3Code;

    /**
     * Aunque manejamos un array con las distintas monedas oficiales en un país,
     * en esta versión tan sólo vamos a emplear la primera moneda que contenga el
     * array por simplicidad
     */
    private List<String> currencies;

    public Country() {
        super();
    }

    public Country(final String countryCode) {
        super();
        this.alpha3Code = countryCode;
    }

    public Country(final String countryCode, final List<String> currencies) {
        super();
        this.alpha3Code = countryCode;
        this.currencies = currencies;
    }

    protected String getAlpha3Code() {
        return alpha3Code;
    }

    protected void setAlpha3Code(final String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    protected List<String> getCurrencies() {
        return currencies;
    }

    protected void setCurrencies(final List<String> currencies) {
        this.currencies = currencies;
    }

    /**
     * Devuelve la primera moneda disponible en el array si existiera
     * @return
     */
    public String getFirstCurrency() {
        if ((currencies != null) && (currencies.size() > 0)) {
            return currencies.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Country [alpha3Code=" + alpha3Code + ", currencies=" + currencies + "]";
    }
}
