package com.nemo.concurrent.td4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(100);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 20, 10, TimeUnit.DAYS, new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.CallerRunsPolicy());

        AtomicInteger count = new AtomicInteger();
        for(int i = 0; i < 100; i ++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                count.getAndIncrement();
                latch.countDown();
            });
        }

        threadPool.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("统计数" + count.get());
    }
}
