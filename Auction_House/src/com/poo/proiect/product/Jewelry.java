package com.poo.proiect.product;

public class Jewelry extends Product {
    private final String material;
    private final boolean gemstone;

    public Jewelry(String name, double minimumPrice, int year, String material, boolean gemstone) {
        super(name, minimumPrice, year);
        this.material = material;
        this.gemstone = gemstone;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Jewelry" +
                ", Material: " + material +
                ", Gemstone: " + gemstone;
    }
}
