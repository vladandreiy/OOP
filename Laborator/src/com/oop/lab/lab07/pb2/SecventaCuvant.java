package com.oop.lab.lab07.pb2;

public class SecventaCuvant implements Secventa {
    private final String[] cuvinte;
    private int index = -1;

    public SecventaCuvant(String text) {
        this.cuvinte = text.split("[\\s]+");
    }

    @Override
    public String parcurge() {
        if (this.hasNext()) {
            index++;
            return cuvinte[index];
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (index + 1 < cuvinte.length)
            return true;
        return false;
    }
}
