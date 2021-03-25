package com.oop.lab.lab07.pb1;

abstract public class Pizza {
    int dimensiune;
    int pret;

    public Pizza(int dimensiune, int pret) {
        this.dimensiune = dimensiune;
        this.pret = pret;
    }

    abstract public String toString();
}
