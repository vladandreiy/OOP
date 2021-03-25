package com.oop.lab.lab07.pb2;

public class Main {
    public static void main(String[] args) {

        SecventaCuvant secventaCuvant = new SecventaCuvant("Ana ARE mere");
        while(secventaCuvant.hasNext()) {
            System.out.println(secventaCuvant.parcurge());
        }

        System.out.println();
        secventaCuvant = new SecventaCuvant("Ana ARE mere");
        Secventa secventaCaracter = new SecventaCaracter(secventaCuvant);
        while(secventaCaracter.hasNext())
            System.out.println(secventaCaracter.parcurge());

        System.out.println();
        secventaCuvant = new SecventaCuvant("Ana ARE mere");
        Secventa secventaMajuscule = new SecventaMajuscule(secventaCuvant);
        while(secventaMajuscule.hasNext())
            System.out.println(secventaMajuscule.parcurge());
    }
}
