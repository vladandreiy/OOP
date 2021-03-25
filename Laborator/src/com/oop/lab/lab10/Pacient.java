package com.oop.lab.lab10;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Pacient implements Comparable<Pacient> {
    String name;
    int priority;

    public Pacient(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(@NotNull Pacient pacient) {
        return Integer.compare(pacient.priority, this.priority);
    }

    @Override
    public String toString() {
        return name + ", " + priority;
    }

    public static void main(String[] args) {
        PriorityQueue<Pacient> pacients = new PriorityQueue<>(Arrays.asList(
                new Pacient("Andrei", 10),
                new Pacient("Andreea", 1),
                new Pacient("Victoras", 49),
                new Pacient("Augustin", 90),
                new Pacient("Marilena", 9),
                new Pacient("Margareta", 4),
                new Pacient("Francisc", 30),
                new Pacient("Hector", 2)));
        while (!pacients.isEmpty())
            System.out.println(pacients.poll());
    }
}
