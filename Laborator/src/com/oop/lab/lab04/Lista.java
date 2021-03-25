package com.oop.lab.lab04;

public class Lista {
    private Nod varf;
    private Nod coada;
    private int contor;

    public Lista() {
        this.varf = new Nod();
        this.coada = this.varf;
        this.contor = 0;
    }

    public void adauga(int x) {
        Nod aux = new Nod(x);
        if (this.dimensiune() == 0) {
            this.varf = aux;
            this.coada = this.varf;
        } else {
            this.coada.urm = aux;
            this.coada = this.coada.urm;
        }
        this.contor++;
    }

    public int dimensiune() {
        return this.contor;
    }

    public Nod getNod(int index) {
        if (index < 0)
            return null;
        if (index >= this.dimensiune())
            return null;
        Nod p = this.varf;
        for (int i = 0; i < index; i++)
            p = p.urm;
        return p;
    }

    public void adaugaPozitie(int index, int x) {
        if (index < 0)
            return;
        if (index >= this.dimensiune())
            return;
        Nod aux = new Nod(x);
        if (index == 0) {
            Nod p = this.varf;
            this.varf = aux;
            this.varf.urm = p;
        }
        else {
            Nod p = getNod(index - 1);
            aux.urm = p.urm;
            p.urm = aux;
        }
        this.contor++;
    }

    public int elementPozitie(int index) {
        if (index < 0)
            return -1;
        if (index >= this.dimensiune())
            return -1;
        return this.getNod(index).val;
    }

    public int elimina(int index) {
        if (index < 0)
            return -1;
        if (index >= this.dimensiune())
            return -1;
        if (this.dimensiune() == 0)
            return -1;
        Nod p = getNod(index);
        Nod ant = getNod(index - 1);
        if (ant != null)
            ant.urm = p.urm;
        else
            this.varf = p.urm;
        this.contor--;
        return p.val;
    }

    public void seteaza(int index, int x) {
        if (index < 0)
            return;
        if (index >= this.dimensiune())
            return;
        if (this.dimensiune() == 0)
            return;
        getNod(index).val = x;
    }

    public void afisareLista() {
        Nod p = this.varf;
        for (int i = 0; i < this.dimensiune(); i++) {
            System.out.print(p.val + " ");
            p = p.urm;
        }
    }

    static class Nod {
        private int val;
        private Nod urm;

        public Nod() {
        }

        public Nod(int x) {
            val = x;
            urm = null;
        }
    }

    public static void main(String[] args) {
        Lista l = new Lista();
        l.adauga(1);
        l.adauga(2);
        l.adauga(4);
        l.adaugaPozitie(2, 3);
        l.adauga(5);

        l.afisareLista();
        System.out.println("\nDimensiunea listei este " + l.dimensiune());
        System.out.println("Elementul de pe pozitia 4 este: " + l.elementPozitie(4));

        System.out.println("Se va elimina elementul " + l.elimina(3) + " de pe pozitia 3");
        l.afisareLista();
        System.out.println("\nSe seteaza elementul de pe pozitia 0 cu 0");
        l.seteaza(0, 0);
        l.afisareLista();
    }
}
