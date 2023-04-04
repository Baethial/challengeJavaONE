package com.alura.conversordivisas.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class CurrencyConverter extends Converter{

    //Constantes
    private static final String API_KEY = "Zgn0HZ3oPSVBYSlUhuIZrE50bUGXbPBV";

    //Atributos
    private Rates rates;

    //Constructor
    public CurrencyConverter(){
        //Monedas soportadas y moneda base
        String currencies = "USD,EUR,GBP,JPY,KRW";
        String base = "COP";
        //Obtener las tasas de cambio de una API
        this.rates = getRates(currencies, base);
    }

    //Metodo para obtener las tasas de conversion
    public Rates getRates(String currencies, String base) {

        //Creacion de la URL de la API
        String url = "https://api.apilayer.com/fixer/latest?symbols=" + currencies + "&base=" + base;

        //Creacion de la peticion
        HttpRequest getRequest = null;
        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("apikey", API_KEY)
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //Creacion del cliente HTTP
        HttpClient httpClient = HttpClient.newHttpClient();

        //Creacion de la respuesta
        HttpResponse<String> getResponse = null;
        try {
            getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        //Crear objeto para traducir JSON
        Gson gson = new Gson();
        //Crear objeto de Java
        rates = gson.fromJson(getResponse.body(), Rates.class);

        return rates;

    }

    //Implementacion del metodo de conversion
    @Override
    public double convert(double amount, String inputSelection, String outputSelection) {
        if (inputSelection == "COP") {
            return amount * rates.getRates().get(outputSelection);
        }
        return amount * (1/rates.getRates().get(inputSelection));
    }

}
