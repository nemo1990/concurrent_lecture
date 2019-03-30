package com.nemo.concurrent.tb6;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号，同一时间执行数量进行限制
 */
public class Demo {

    public void method(Semaphore semaphore) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " is run ...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.release();
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        Semaphore semaphore = new Semaphore(10);

        //容易挂机..线程太多了 但是同一时间在执行方法的最多只有10个
        while (true) {
            new Thread(() -> d.method(semaphore));
        }


    }
}
