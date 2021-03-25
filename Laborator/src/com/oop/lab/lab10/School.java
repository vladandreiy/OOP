package com.oop.lab.lab10;

import java.util.*;

public class School {
    private final Map<Integer, ArrayList<Student>> studentGradesMap;

    public School() {
        this.studentGradesMap = new TreeMap<>(Comparator.reverseOrder());
    }

    public void addStudents(List<Student> studentsList) {
        for (Student student : studentsList) {
            int roundedGrade = (int) (Math.round(student.getAverageGrade()));
            if (studentGradesMap.containsKey(roundedGrade)) {
                studentGradesMap.get(roundedGrade).add(student);
                Collections.sort(studentGradesMap.get(roundedGrade));
            } else {
                studentGradesMap.put(roundedGrade, new ArrayList<>(Collections.singletonList((student))));
            }
        }
    }

    public static void main(String[] args) {
        School school = new School();
        school.addStudents(new ArrayList<>(Arrays.asList
                (new Student("Andrei", 8.50),
                        new Student("Andreea", 10),
                        new Student("Victoras", 7.49),
                        new Student("Augustin", 9.90),
                        new Student("Marilena", 7.9),
                        new Student("Margareta", 9.04),
                        new Student("Francisc", 5.30),
                        new Student("Hector", 8.89))));
        for (Map.Entry<Integer, ArrayList<Student>> students : school.studentGradesMap.entrySet()) {
            System.out.print("Studentii cu media " + students.getKey() + ":\n");
            for (Student student : students.getValue()) {
                System.out.println("\t" + student);
            }
        }
    }
}
