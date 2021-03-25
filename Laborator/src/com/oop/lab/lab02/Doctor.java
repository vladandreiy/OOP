package com.oop.lab.lab02;

import java.util.Arrays;

public class Doctor {
    private String nume;

    public Doctor() {
    }

    public Doctor(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public static void printAllDoctors(Doctor[] doctors) {
        for (Doctor doctor : doctors) {
            System.out.println(doctor.toString());
        }
        System.out.println();
    }

    public static void printOccupation(Doctor[] doctors) {
        for (Doctor doctor : doctors) {
            if (doctor instanceof Chirurg)
                ((Chirurg) doctor).opereaza();
            if (doctor instanceof Generalist)
                ((Generalist) doctor).prescrieRetete();
        }
    }

    @Override
    public String toString() {
        return this.getNume();
    }

    public static void main(String[] args) {
        Doctor[] doctors = new Doctor[5];
        doctors[0] = new Chirurg("Andrei", "neurochirurg");
        doctors[1] = new Generalist("Vlad", 20);
        doctors[2] = new Chirurg("Andrei", "neurochirurg");
        doctors[3] = new Generalist("Marius", 5);
        doctors[4] = new Generalist("Mihai", 25);

        Doctor.printAllDoctors(doctors);

        Doctor.printOccupation(doctors);

        int count = 0;
        for (int i = 0; i < doctors.length - 1; i++) {
            for (int j = i + 1; j < doctors.length; j++) {
                if (doctors[i] instanceof Chirurg && doctors[j] instanceof Chirurg) {
                    if (((Chirurg) doctors[i]).equals(((Chirurg) doctors[j])))
                        count++;
                }
            }
        }
        if (count > 0)
            System.out.println("\nExista cel putin 2 chirurgi cu acelasi nume si aceeasi expertiza");

        Arrays.sort(doctors, new SortGeneralists());
        System.out.println("\nVectorul dupa sortare:");
        Doctor.printAllDoctors(doctors);

        count = 0;
        for (Doctor doctor : doctors) {
            if (doctor instanceof Generalist) {
                if (((Generalist) doctor).isAboveAverage()) {
                    count++;
                }
            }
        }
        System.out.println(count + " medici generalisti au numarul de pacienti mai mare decat media.");
    }
}
