package com.oop.lab.lab01;

import java.util.Scanner;

public class NumarRational {
    private int n = 0;
    private int m = 1;

    NumarRational() {

    }

    NumarRational(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }

    public boolean maiMare(NumarRational r) {
        return this.n * r.m > this.m * r.n;
    }

    public static NumarRational add(NumarRational a, NumarRational b) {
        NumarRational result = new NumarRational();
        result.setM(a.getM() * b.getM());
        result.setN(a.getN() * b.getM() + a.getM() * b.getN());
        result.m = a.m * b.m;
        result.n = a.n * b.m + a.m * b.n;
        return result;
    }

    public static NumarRational multiply(NumarRational a, NumarRational b) {
        NumarRational result = new NumarRational();
        result.setN(a.getN() * b.getN());
        result.setM(a.getM() * b.getM());
        return result;
    }

    public void read() {
        Scanner s = new Scanner(System.in);
        this.setN(s.nextInt());
        this.setM(s.nextInt());
    }

    public void print() {
        System.out.print(this.n + "/" + this.m + " ");
    }

    public void println() {
        System.out.println(this.n + "/" + this.m);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("x = ");
        NumarRational x = new NumarRational();
        x.read();
        x.println();
        System.out.print("y = ");
        NumarRational y = new NumarRational();
        y.read();
        y.println();
        NumarRational sum = NumarRational.add(x, y);
        System.out.print("Suma numerelor este ");
        sum.println();
        System.out.print("k = ");
        int k = s.nextInt();
        System.out.println("Se vor citi " + k + " numere rationale");
        NumarRational[] v = new NumarRational[k];
        sum = new NumarRational();
        for (int i = 0; i < k; i++) {
            v[i] = new NumarRational();
            v[i].read();
            sum = NumarRational.add(sum, v[i]);
        }
        System.out.print("Suma tuturor numerelor este: ");
        sum.println();
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length; j++) {
                if (v[j].maiMare(v[i])) {
                    new NumarRational();
                    NumarRational aux = v[j];
                    v[j] = v[i];
                    v[i] = aux;
                }
            }
        }
        System.out.println("Vectorul ordonat este:");
        for (NumarRational numarRational : v) {
            numarRational.print();
        }
    }
}
