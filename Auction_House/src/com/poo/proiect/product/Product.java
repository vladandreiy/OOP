package com.poo.proiect.product;

public abstract class Product {
    private final int id;
    private final String name;
    private double sellingPrice;
    private final double minimumPrice;
    private final int year;
    private static int productsNo;

    protected Product(String name, double minimumPrice, int year) {
        this.name = name;
        this.minimumPrice = minimumPrice;
        this.year = year;
        this.id = productsNo++;
    }

    @Override
    public String toString() {
        String printString = "Id: " + id +
                ", Name: " + name;
        if (sellingPrice < 0)
            printString += ", Selling Price: " + sellingPrice;
        printString += ", Minimum Price: " + minimumPrice +
                ", Year: " + year;
        return printString;
    }

    public void setSellingPrice(double sellingPrice) throws NullOrNegativePriceException {
        if (sellingPrice < 0)
            throw new NullOrNegativePriceException("Selling price cannot be negative " + sellingPrice);
        this.sellingPrice = sellingPrice;
    }

    public int getId() {
        return id;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }
}
