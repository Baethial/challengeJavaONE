package com.alura.conversordivisas.model;

import java.util.Map;

public class Rates {
    //Atributos
    public Map<String, Double> rates;

    //Getters y Setters
    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
