package com.oop.lab.lab11;

import java.util.Random;

public class Producer implements Runnable {
    private final SynchronizedArray synchronizedArray;

    public Producer(SynchronizedArray synchronizedArray) {
        this.synchronizedArray = synchronizedArray;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int randomNumber = new Random().nextInt(100);
                System.out.println("Producer writes " + randomNumber);
                synchronizedArray.write(randomNumber);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
