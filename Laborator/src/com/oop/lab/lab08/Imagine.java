package com.oop.lab.lab08;

public class Imagine {
    int lungime;
    int latime;
    int nivelLuminozitate;

    public Imagine(int lungime, int latime, int nivelLuminozitate) {
        this.lungime = lungime;
        this.latime = latime;
        this.nivelLuminozitate = nivelLuminozitate;
    }

    public void marire() {
        lungime *= 1.5;
        latime *= 1.5;
    }

    public void micsorare() {
        lungime /= 3.0 / 2;
        latime /= 3.0 / 2;
    }

    public void decupare() {
        lungime -= 30;
        latime -= 20;
    }

    public void lipire() {
        lungime += 30;
        latime += 20;
    }

    public void crestereLuminozitate() {
        nivelLuminozitate++;
    }

    public void scadereLuminozitate() {
        nivelLuminozitate--;
    }

    @Override
    public String toString() {
        return "Imagine{" +
                "lungime=" + lungime +
                ", latime=" + latime +
                ", nivelLuminozitate=" + nivelLuminozitate +
                '}';
    }
}
