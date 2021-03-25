package com.oop.lab.lab08;

import java.util.ArrayList;

public class Editor {
    int index = 0;
    public ArrayList<Comanda> listaComenzi = new ArrayList<>(10);

    public void adaugaComanda(Comanda comanda) {
        listaComenzi.add(index, comanda);
        index++;
    }

    public void executaComenzi(Imagine imagine) {
        for (int i = 0; i < index; i++) {
            listaComenzi.get(i).executa(imagine);
        }
    }

    public void reexecuta(Imagine imagine) {
        if (index >= 1) {
            listaComenzi.get(index - 1).executa(imagine);
            adaugaComanda(listaComenzi.get(index - 1));
        }
    }

    public void anuleaza(Imagine imagine) {
        if (index >= 1) {
            listaComenzi.get(index - 1).anuleaza(imagine);
            index--;
        }
    }
}
