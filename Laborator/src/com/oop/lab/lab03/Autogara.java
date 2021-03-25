package com.oop.lab.lab03;

import java.util.Arrays;

public class Autogara {
    private MijlocTransport[] mijloace;

    public Autogara() {
    }

    public Autogara(int n) {
        mijloace = new MijlocTransport[n];
    }

    public void afiseazaAutogara() {
        for (MijlocTransport mijloc : mijloace) {
            mijloc.afiseaza();
        }
    }

    public int profitTotal() {
        int profitTotal = 0;
        for (MijlocTransport mijloc : mijloace)
            profitTotal += mijloc.profit();
        return profitTotal;
    }

    public void sortare() {
        Arrays.sort(mijloace);
    }

    public static void main(String[] args) {
        new Object();
        Autogara autogara = new Autogara(4);
        autogara.mijloace[0] = new Microbuz("albastru", true, 1, 100);
        autogara.mijloace[1] = new Microbuz("rosu", true, 20, 2);
        autogara.mijloace[2] = new Autobuz("verde", false, 33, 3);
        autogara.mijloace[3] = new Autobuz("galben", true, 10, 5);
        autogara.afiseazaAutogara();
        System.out.println("Profitul total este: " + autogara.profitTotal());
        autogara.sortare();
        System.out.println("Autogara dupa sortare");
        autogara.afiseazaAutogara();
    }
}
