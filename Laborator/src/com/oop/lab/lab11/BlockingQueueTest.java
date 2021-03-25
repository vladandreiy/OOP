package com.oop.lab.lab11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerBlockingQueue(blockingQueue));
        executor.execute(new ConsumerBlockingQueue(blockingQueue));
        executor.shutdown();
    }
}
