package com.oop.lab.lab10;

import org.jetbrains.annotations.NotNull;

public class Student implements Comparable<Student> {
    private final String name;
    private final double averageGrade;

    public Student(String name, double averageGrade) {
        this.name = name;
        this.averageGrade = averageGrade;
    }

    public String getName() {
        return name;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public int compareTo(@NotNull Student student) {
        return Double.compare(student.getAverageGrade(), this.getAverageGrade());
    }


    @Override
    public String toString() {
        return name + ", " + averageGrade;
    }
}
