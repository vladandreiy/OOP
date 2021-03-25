package com.poo.proiect.product;

public class Furniture extends Product {
    private final String type;
    private final String material;

    public Furniture(String name, double minimumPrice, int year, String type, String material) {
        super(name, minimumPrice, year);
        this.type = type;
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Furniture" +
                ", Type: " + type +
                ", Material: " + material;
    }
}
