package com.oop.lab.lab05;

public class Cont {
    private int numarCont;
    private int depozit;

    public Cont(int numarCont, int depozit) {
        this.numarCont = numarCont;
        this.depozit = depozit;
    }

    public void depune(int suma) {
        this.depozit += suma;
    }

    public void retrage(int suma) throws ExceptieFonduriInsuficiente {
        if (suma > this.depozit)
            throw new ExceptieFonduriInsuficiente();
        this.depozit -= suma;
    }

    public int getDepozit() {
        return depozit;
    }

    public void printCont() {
        System.out.println("Numar cont: " + this.numarCont + ", Depozit: " + this.depozit);
    }

}
