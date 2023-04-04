package com.alura.conversordivisas.model;

public abstract class Converter {

    /// Metodo abstracto para realizar una conversion
    abstract public double convert(double amount, String inputSelection, String outputSelection);

}
