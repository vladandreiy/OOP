package com.oop.lab.lab11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ConsumerBlockingQueue implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public ConsumerBlockingQueue(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                List<Integer> list = new ArrayList<>();
                blockingQueue.drainTo(list);
                System.out.println("\t\t\tConsumer reads " + list);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
