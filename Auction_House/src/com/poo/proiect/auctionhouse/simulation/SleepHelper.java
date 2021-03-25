package com.poo.proiect.auctionhouse.simulation;

public class SleepHelper {
    private SleepHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
