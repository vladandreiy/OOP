package com.oop.lab.lab07.pb1;

public class PepperoniPizza extends Pizza {
    public PepperoniPizza(int dimensiune, int pret) {
        super(dimensiune, pret);
    }

    @Override
    public String toString() {
        return "PepperoniPizza, dimensiune=" + super.dimensiune + ", pret=" + super.pret;
    }
}
