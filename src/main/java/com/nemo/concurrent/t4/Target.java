package com.nemo.concurrent.t4;

public class Target implements Runnable{

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " ...");
        }
    }
}
