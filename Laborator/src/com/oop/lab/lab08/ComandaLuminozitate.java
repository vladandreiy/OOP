package com.oop.lab.lab08;

public class ComandaLuminozitate implements Comanda {
    @Override
    public void executa(Imagine imagine) {
        imagine.crestereLuminozitate();
    }

    @Override
    public void anuleaza(Imagine imagine) {
        imagine.scadereLuminozitate();
    }
}
