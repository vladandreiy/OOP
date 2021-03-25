package com.oop.lab.lab02;

public class Generalist extends Doctor {
    private int nrPacienti;
    static int total_nrPacienti = 0;
    static int nrDoctori = 0;

    public Generalist() {
    }

    public Generalist(int nrPacienti) {
        this.nrPacienti = nrPacienti;
        total_nrPacienti += nrPacienti;
        nrDoctori++;
    }

    public Generalist(String nume, int nrPacienti) {
        super(nume);
        this.nrPacienti = nrPacienti;
        total_nrPacienti += nrPacienti;
        nrDoctori++;
    }

    public int getNrPacienti() {
        return nrPacienti;
    }

    @Override
    public String toString() {
        return super.toString() + ", numar pacienti: " + getNrPacienti();
    }

    public void prescrieRetete() {
        System.out.println(this.getNume() + " prescrie retete");
    }

    public static int getTotal_nrPacienti() {
        return total_nrPacienti;
    }

    public static int getNrDoctori() {
        return nrDoctori;
    }

    public static float getMediePacienti() {
        return getTotal_nrPacienti()/getNrDoctori();
    }

    public boolean isAboveAverage() {
        return this.getNrPacienti() > getMediePacienti();
    }
}
