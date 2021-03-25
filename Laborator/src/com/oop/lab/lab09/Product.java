package com.oop.lab.lab09;

import org.jetbrains.annotations.NotNull;

public abstract class Product implements Comparable<Product> {
    public abstract double pretRaft();

    public abstract String toString();

    @Override
    public int compareTo(@NotNull Product product) {
        if (this.pretRaft() > product.pretRaft())
            return 1;
        else if (this.pretRaft() < product.pretRaft())
            return -1;
        return 0;
    }
}
