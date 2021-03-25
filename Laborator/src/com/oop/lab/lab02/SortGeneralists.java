package com.oop.lab.lab02;

import java.util.Comparator;

public class SortGeneralists implements Comparator<Doctor> {
    public int compare (Doctor x, Doctor y){
        if(x instanceof Generalist && y instanceof Generalist)
            return ((Generalist) x).getNrPacienti() - ((Generalist) y).getNrPacienti();
        return -1;
    }
}
