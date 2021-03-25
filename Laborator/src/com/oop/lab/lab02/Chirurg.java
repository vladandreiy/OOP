package com.oop.lab.lab02;

public class Chirurg extends Doctor {
    private String expertiza;

    public Chirurg() {
    }

    public Chirurg(String expertiza) {
        this.expertiza = expertiza;
    }

    public Chirurg(String nume, String expertiza) {
        super(nume);
        this.expertiza = expertiza;
    }

    public String getExpertiza() {
        return expertiza;
    }

    @Override
    public String toString() {
        return super.toString() + ", expertiza: " + expertiza;
    }

    public void opereaza() {
        System.out.println(this.getNume() + " opereaza");
    }

    public boolean equals(Chirurg chirurg) {
        if(this.getNume().equals(chirurg.getNume())) {
            return this.getExpertiza().equals(chirurg.getExpertiza());
        }
        return false;
    }
}
