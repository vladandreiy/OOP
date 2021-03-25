package com.oop.lab.lab09;

public class ProdusCuratenie extends Product {
    private double pretProducator;

    ProdusCuratenie(double pretProducator) {
        this.pretProducator = pretProducator;
    }

    @Override
    public double pretRaft() {
        return pretProducator + pretProducator * 15 / 100;
    }

    @Override
    public String toString() {
        return "ProdusCuratenie{" +
                "pretProducator=" + pretProducator +
                '}';
    }
}
