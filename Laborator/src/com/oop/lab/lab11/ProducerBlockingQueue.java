package com.oop.lab.lab11;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ProducerBlockingQueue implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public ProducerBlockingQueue(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int randomNumber = new Random().nextInt(100);
                System.out.println("Producer writes " + randomNumber);
                blockingQueue.put(randomNumber);
                Thread.sleep(700);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
