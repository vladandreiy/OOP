package com.poo.proiect.auctionhouse.simulation;

import java.util.Random;

public class RandomHelper {
    private RandomHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static int getBid(int max) {
        if (max <= 0)
            max = getRandomInt(400, 700);
        int min;
        if (max >= 1000) {
            min = max - 300;
            max = max + 200;
        } else {
            min = max - (int) (40.0 / 100 * max);
            max = max + (int) (25.0 / 100 * max);
        }
        return getRandomInt(min, max);
    }

    public static int getRandomInt(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public static boolean getClientAnswer() {
        return new Random().nextInt(10) >= 8;
    }
}
