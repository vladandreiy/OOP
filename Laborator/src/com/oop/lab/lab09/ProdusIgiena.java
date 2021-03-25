package com.oop.lab.lab09;

public class ProdusIgiena extends Product {
    private double pretProducator;

    ProdusIgiena(double pretProducator) {
        this.pretProducator = pretProducator;
    }

    @Override
    public double pretRaft() {
        return pretProducator + pretProducator * 10 / 100;
    }

    @Override
    public String toString() {
        return "ProdusIgiena{" +
                "pretProducator=" + pretProducator +
                '}';
    }
}
