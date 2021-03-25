package com.oop.lab.lab09;

public class ProdusAlimentar extends Product {
    private double pretProducator;

    ProdusAlimentar(double pretProducator) {
        this.pretProducator = pretProducator;
    }

    @Override
    public double pretRaft() {
        return pretProducator + pretProducator * 20 / 100;
    }

    @Override
    public String toString() {
        return "ProdusAlimentar{" +
                "pretProducator=" + pretProducator +
                '}';
    }
}
