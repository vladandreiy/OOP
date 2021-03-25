package com.oop.lab.lab11;

import java.util.Random;

public class Consumer implements Runnable {
    private final SynchronizedArray synchronizedArray;

    public Consumer(SynchronizedArray synchronizedArray) {
        this.synchronizedArray = synchronizedArray;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int randomNumber = new Random().nextInt(100);
                System.out.println("\t\t\tConsumer reads " + synchronizedArray.read());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
