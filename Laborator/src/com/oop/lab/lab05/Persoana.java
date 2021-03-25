package com.oop.lab.lab05;

public class Persoana {
    private static int nrPersoane;
    private Cont cont;
    private String nume;
    private String cnp;

    public void seteazaDate(String nume, String cnp, Cont cont) throws ExceptieCnpInvalid {
        this.cont = cont;
        this.nume = nume;
        if(cnp.charAt(0) != '1')
            if(cnp.charAt(0) != '2')
                throw new ExceptieCnpInvalid();
        this.cnp = cnp;
    }

    public Persoana(String nume, String cnp, Cont cont) throws ExceptieCnpInvalid {
        this.seteazaDate(nume, cnp, cont);
        nrPersoane++;
    }

    public Cont getCont() {
        return cont;
    }

    public String getNume() {
        return nume;
    }

    public void afiseazaInformatii() {
        System.out.print(this.nume + ", CNP:" + this.cnp + ", ");
        this.cont.printCont();
    }

    public static int getNrPersoane() {
        return nrPersoane;
    }
}
