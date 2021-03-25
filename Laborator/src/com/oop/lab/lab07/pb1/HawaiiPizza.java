package com.oop.lab.lab07.pb1;

public class HawaiiPizza extends Pizza {
    public HawaiiPizza(int dimensiune, int pret) {
        super(dimensiune, pret);
    }

    @Override
    public String toString() {
        return "HawaiiPizza, dimensiune=" + super.dimensiune + ", pret=" + super.pret;
    }
}
