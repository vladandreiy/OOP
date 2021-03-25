package com.oop.lab.lab09;

import java.util.ArrayList;
import java.util.Collections;

public class Magazin {
    public static void pretRaftMaxim(ArrayList<? extends Product> list) {
        Collections.sort(list);
        System.out.println(list.get(list.size() - 1) + ", pret raft: " + list.get(list.size() - 1).pretRaft());
    }

    public static void pretRaftMaximPerProdus(ArrayList<? extends Product> list) {
        ArrayList<Product> list1 = new ArrayList<>();
        ArrayList<Product> list2 = new ArrayList<>();
        ArrayList<Product> list3 = new ArrayList<>();
        for(Product product: list) {
            if(product instanceof ProdusAlimentar)
                list1.add(product);
            else if(product instanceof ProdusCuratenie)
                list2.add(product);
            else
                list3.add(product);
        }
        pretRaftMaxim(list1);
        pretRaftMaxim(list2);
        pretRaftMaxim(list3);
    }

    public static void main(String[] args) {
        ArrayList<Product> magazin = new ArrayList<>();
        magazin.add(new ProdusAlimentar(10));
        magazin.add(new ProdusCuratenie(10));
        magazin.add(new ProdusIgiena(10));
        magazin.add(new ProdusAlimentar(20));
        magazin.add(new ProdusCuratenie(20));
        magazin.add(new ProdusIgiena(20));
        pretRaftMaxim(magazin);
        pretRaftMaximPerProdus(magazin);
    }
}
