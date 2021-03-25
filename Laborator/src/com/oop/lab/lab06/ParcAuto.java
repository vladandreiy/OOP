package com.oop.lab.lab06;

import java.io.*;

public class ParcAuto {
    private final String numeFisier;

    public ParcAuto(String numeFisier) {
        this.numeFisier = numeFisier;
    }

    public int numaraMasiniNoi() throws IOException {
        FileReader fileReader = new FileReader(numeFisier);
        BufferedReader reader = new BufferedReader(fileReader);
        Auto car;
        int newCars = 0;
        while ((car = Auto.citeste(reader)) != null) {
            if(car.getKm() == 0)
                newCars++;
        }
        reader.close();
        fileReader.close();
        return newCars;
    }

    public boolean cauta(Auto a) throws IOException {
        FileReader fileReader = new FileReader(numeFisier);
        BufferedReader reader = new BufferedReader(fileReader);
        Auto car;
        while ((car = Auto.citeste(reader)) != null) {
            if(car.identic(a)) {
                reader.close();
                fileReader.close();
                return true;
            }
        }
        reader.close();
        fileReader.close();
        return false;
    }

    public Auto celMaiScumpAuto() throws IOException {
        FileReader fileReader = new FileReader(numeFisier);
        BufferedReader reader = new BufferedReader(fileReader);
        Auto car;
        Auto scump = null;
        double maxCost = 0.0;
        while ((car = Auto.citeste(reader)) != null) {
            if(car.getPret() > maxCost) {
                scump = car;
                maxCost = car.getPret();
            }
        }
        reader.close();
        fileReader.close();
        return scump;
    }

    public void adaugaAuto(Auto a) throws IOException {
        FileWriter fileWriter = new FileWriter(numeFisier, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(a.getModel() + "\n");
        bufferedWriter.write(a.getAn() + "\n");
        bufferedWriter.write(a.getKm() + "\n");
        bufferedWriter.write(a.getPret() + "\n");
        bufferedWriter.close();
        fileWriter.close();
    }

    public void afiseazaParcAuto() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(numeFisier));
        Auto car;
        while ((car = Auto.citeste(reader)) != null) {
            System.out.println(car);
        }
        reader.close();
    }

    public static void main(String[] args) {
        try {
            ParcAuto p = new ParcAuto("/home/vlad/IdeaProjects/Poo/src/com/poo/lab/lab06/date.txt");
            p.afiseazaParcAuto();
            System.out.println("\nNumar de masini noi in parc: " + p.numaraMasiniNoi());
            System.out.println("\nCea mai scumpa masina:\n" + p.celMaiScumpAuto());
            Auto newCar = new Auto("Ford", 2016, 420, 10000.0);
            if (p.cauta(newCar))
                System.out.println("\nMasina\t" + newCar + " exista in parcul auto");
            else {
                System.out.println("\nMasina\t" + newCar + " nu exista in parcul auto\n");
                p.adaugaAuto(newCar);
                p.afiseazaParcAuto();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
