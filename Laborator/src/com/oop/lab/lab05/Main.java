package com.oop.lab.lab05;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Persoana> persoane = new ArrayList<>(4);
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            String nume = s.next();
            String cnp = s.next();
            Cont cont = new Cont(s.nextInt(), s.nextInt());
            try {
                persoane.add(new Persoana(nume, cnp, cont));
            } catch (ExceptieCnpInvalid exceptieCnpInvalid) {
                System.out.println("CNP-ul " + cnp + " al persoanei " + nume + " nu este valid!!!!");
            }
        }

        for (int i = 0; i < Persoana.getNrPersoane(); i++) {
            persoane.get(i).afiseazaInformatii();
        }

        System.out.println("\nRetrageri\n");

        for (int i = 0; i < Persoana.getNrPersoane(); i++) {
            try {
                persoane.get(i).getCont().retrage(1000);
                persoane.get(i).afiseazaInformatii();
            } catch (ExceptieFonduriInsuficiente exceptieFonduriInsuficiente) {
                System.out.print(persoane.get(i).getNume() + " a incercat sa retraga suma de " + 1000);
                System.out.print(" de lei dar are doar " + persoane.get(i).getCont().getDepozit() + " lei\n");
            }
        }
    }

}
