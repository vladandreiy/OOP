package com.oop.lab.lab03;

import org.jetbrains.annotations.NotNull;

public abstract class MijlocTransport implements Comparable {
    private String culoare;
    private boolean functional;

    public MijlocTransport() {
        this.culoare = "alb";
        this.functional = false;
    }

    public MijlocTransport(String culoare, boolean functional) {
        this.culoare = culoare;
        this.functional = functional;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public boolean isFunctional() {
        return functional;
    }

    public void setFunctional(boolean functional) {
        this.functional = functional;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.profit() - ((MijlocTransport)o).profit();
    }

    public abstract int incasare();
    public abstract int profit();
    public abstract void afiseaza();

}
