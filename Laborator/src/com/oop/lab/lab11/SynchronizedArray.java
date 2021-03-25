package com.oop.lab.lab11;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedArray {
    static final int CAPACITY = 3;
    private LinkedList<Integer> list;

    private static Lock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();

    public SynchronizedArray() {
        this.list = new LinkedList<>();
    }

    public void write(int value) {
        lock.lock();
        try {
            while (list.size() == CAPACITY) {
                System.err.println("List is full, waiting");
                notFull.await();
            }
            list.offer(value);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public int read() {
        lock.lock();
        int value = 0;
        try {
            while (list.isEmpty()) {
                System.err.println("List is empty, waiting");
                notEmpty.await();
            }
            value = list.getFirst();
            list.remove(list.getFirst());
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return value;
    }


    public static void main(String[] args) {
        SynchronizedArray synchronizedArray = new SynchronizedArray();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Producer(synchronizedArray));
        executor.execute(new Consumer(synchronizedArray));
        executor.shutdown();
    }
}
