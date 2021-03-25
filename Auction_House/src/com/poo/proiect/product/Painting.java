package com.poo.proiect.product;

public class Painting extends Product {
    private final String artistName;
    private final PaintingType paintingType;

    public Painting(String name, double minimumPrice, int year, String artistName, String paintingType) {
        super(name, minimumPrice, year);
        this.artistName = artistName;
        this.paintingType = PaintingType.valueOf(paintingType.toUpperCase());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Painting" +
                ", Artist Name: " + artistName +
                ", Painting Type: " + paintingType;
    }
}
