package com.nemo.concurrent.tb5;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier  屏障，达到一定数量才能继续执行
 */
public class Demo {

    Random random = new Random();

    public void meeting(CyclicBarrier barrier) {
        try {
            Thread.sleep(random.nextInt(4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 到达会议室，等待开会...");
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //必须10个await 每个等待线程才能继续执行
        System.out.println(Thread.currentThread().getName() + " 发言");
    }

    public static void main(String[] args) {
        Demo d = new Demo();

        //一定要10个await才会执行barrierAction的内容
        CyclicBarrier barrier = new CyclicBarrier(10, () -> System.out.println("好！我们开始开会..."));

        for(int i = 0; i < 10; i++) {
            new Thread(() -> d.meeting(barrier)).start();
        }
    }
}
