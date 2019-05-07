package com.nemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    private static Object object = new Object();


    public static void main(String[] args) {
//        for(int i = 0; i < 10; i++) {
//            new Print(i, object).start();
//        }

//        System.out.println(System.currentTimeMillis());
//        System.out.println("我是主线程");
//
//        Thread t = new Thread(new UnParkMain(Thread.currentThread()));
//        t.setDaemon(true);
//
//        t.start();
//
//        LockSupport.park();
//        System.out.println("我被取消挂机了，继续执行");

        Lock lock = new ReentrantLock();


        Thread t1 = new Thread(new RunInterrupt(lock), "a");


        Thread t2 = new Thread(new RunInterrupt(lock), "b");


        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();


    }
}


class RunInterrupt implements Runnable {

    private Lock lock;

    public RunInterrupt(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "获取到了锁");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "并没有获取到锁，不用释放");
            }
        }
    }
}

class UnParkMain implements Runnable {

    private Thread t;

    public UnParkMain(Thread t) {
        this.t = t;
    }

    @Override
    public void run() {
        System.out.println("等5s我就取消主线程的挂起");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(t);
    }
}

class Print extends Thread {

    private int count;

    private Object object;

    public Print(final int count, Object object) {
        this.count = count;
        this.object = object;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(count * 1000);

            synchronized (object) {
                if (count == 9) {
                    object.notify();
                } else {
                    System.out.println("wait " + count);
                    object.wait();
                }
            }

            System.out.println("end wait" + count);

        } catch (InterruptedException e) {
            System.out.println(count);
        }


    }
}