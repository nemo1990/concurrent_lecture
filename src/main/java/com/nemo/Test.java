package com.nemo;

import java.util.concurrent.locks.LockSupport;

public class Test {

    private static Object object = new Object();


    public static void main(String[] args) {
//        for(int i = 0; i < 10; i++) {
//            new Print(i, object).start();
//        }

        System.out.println(System.currentTimeMillis());
        System.out.println("我是主线程");

        Thread t = new Thread(new UnParkMain(Thread.currentThread()));
        t.setDaemon(true);

        t.start();

        LockSupport.park();
        System.out.println("我被取消挂机了，继续执行");

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