package com.oop.lab.lab08;

public class TestImagine {
    public static void main(String[] args) {
        Editor editor = new Editor();
        Imagine imagine = new Imagine(1000, 1000, 5);
        editor.adaugaComanda(new ComandaRedimensionare());
        editor.adaugaComanda(new ComandaLuminozitate());
        editor.adaugaComanda(new ComandaDecupare());

        editor.executaComenzi(imagine);
        System.out.println(imagine);

        editor.anuleaza(imagine);
        System.out.println(imagine);

        editor.anuleaza(imagine);
        System.out.println(imagine);

        editor.reexecuta(imagine);
        System.out.println(imagine);

        editor.anuleaza(imagine);
        editor.anuleaza(imagine);
        editor.anuleaza(imagine);
        editor.anuleaza(imagine);
        editor.anuleaza(imagine);
        System.out.println(imagine);
    }
}