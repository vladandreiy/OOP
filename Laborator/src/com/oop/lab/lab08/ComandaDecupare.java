package com.oop.lab.lab08;

public class ComandaDecupare implements Comanda {

    @Override
    public void executa(Imagine imagine) {
        imagine.decupare();
    }

    @Override
    public void anuleaza(Imagine imagine) {
        imagine.lipire();
    }
}
