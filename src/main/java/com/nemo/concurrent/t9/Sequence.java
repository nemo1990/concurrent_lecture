package com.nemo.concurrent.t9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sequence {

    private int value;

    Lock lock = new ReentrantLock();

    public int getNext() {
        lock.lock();
        int a = value ++;
        lock.unlock();
        return a;
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();

        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }


}
