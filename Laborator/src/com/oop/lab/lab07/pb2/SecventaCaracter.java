package com.oop.lab.lab07.pb2;

public class SecventaCaracter extends SecventaDecorator {
    private int index = -1;
    private String cuv = super.parcurge();

    public SecventaCaracter(SecventaCuvant s) {
        super(s);
    }

    @Override
    public String parcurge() {
        if (this.hasNext()) {
            // Daca e ultima litera din cuvant
            if (index == cuv.length() - 1) {
                cuv = super.parcurge();
                index = -1;
            }
            index++;
            return cuv.charAt(index) + "";
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return super.hasNext() || index + 1 < cuv.length();
    }
}