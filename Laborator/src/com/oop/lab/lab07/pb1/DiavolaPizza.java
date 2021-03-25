package com.oop.lab.lab07.pb1;

public class DiavolaPizza extends Pizza {
    public DiavolaPizza(int dimensiune, int pret) {
        super(dimensiune, pret);
    }

    @Override
    public String toString() {
        return "DiavolaPizza, dimensiune=" + super.dimensiune + ", pret=" + super.pret;
    }
}
