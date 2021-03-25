package com.oop.lab.lab06;

import java.io.BufferedReader;
import java.io.IOException;

public class Auto {
    private final String model;
    private final int an;
    private final int km;
    private final double pret;

    public Auto(String model, int an, int km, double pret) {
        this.model = model;
        this.an = an;
        this.km = km;
        this.pret = pret;
    }

    public String getModel() {
        return model;
    }

    public int getAn() {
        return an;
    }

    public int getKm() {
        return km;
    }

    public double getPret() {
        return pret;
    }

    @Override
    public String toString() {
        return "Model: " + model + ", an de fabricatie: " + an + ", KM parcursi: " + km + ", pret:" + pret + " EUR";
    }

    public boolean identic(Auto car) {
        if (car == null) return false;

        if (this.an != car.getAn()) return false;
        if (this.km != car.getKm()) return false;
        if (Double.compare(car.pret, this.pret) != 0) return false;
        if (this.model == null)
            return car.getModel() == null;
        return (this.model.equals(car.getModel()));
    }

    public static Auto citeste(BufferedReader br) throws IOException {
        String buffer;
        buffer = br.readLine();
        if (buffer == null)
            return null;
        String model = buffer;

        buffer = br.readLine();
        if (buffer == null)
            return null;
        int an = Integer.parseInt(buffer);

        buffer = br.readLine();
        if (buffer == null)
            return null;
        int km = Integer.parseInt(buffer);

        buffer = br.readLine();
        if (buffer == null)
            return null;
        double pret = Double.parseDouble(buffer);

        return new Auto(model, an, km, pret);
    }
}
