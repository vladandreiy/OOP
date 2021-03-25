package com.oop.lab.lab03;

public class Autobuz extends MijlocTransport {
    private int numarPasageri;
    private int pretBilet;

    public Autobuz() {
        this.numarPasageri = 0;
        this.pretBilet = 0;
    }

    public Autobuz(String culoare, boolean functional, int numarPasageri, int pretBilet) {
        super(culoare, functional);
        this.numarPasageri = numarPasageri;
        this.pretBilet = pretBilet;
    }

    public int getNumarPasageri() {
        return numarPasageri;
    }

    public void setNumarPasageri(int numarPasageri) {
        this.numarPasageri = numarPasageri;
    }

    public int getPretBilet() {
        return pretBilet;
    }

    public void setPretBilet(int pretBilet) {
        this.pretBilet = pretBilet;
    }

    @Override
    public int incasare() {
        return this.getNumarPasageri() * this.getPretBilet();
    }

    @Override
    public int profit() {
        return this.incasare() / 4;
    }

    @Override
    public void afiseaza() {
        System.out.println("Autobuz (" + this.getCuloare() + ", " + this.isFunctional() + ") cu " + this.getNumarPasageri() + " si " + this.getPretBilet());
    }
}
