package com.nemo.concurrent.t6;

public class Demo {

    public synchronized void a() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a");
//        b();
    }

    public synchronized void b() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("b");
    }

    public static void main(String[] args) {
        Demo d1 = new Demo();
        Demo d2 = new Demo();

        new Thread(() -> d1.a()).start();
        new Thread(() -> d2.b()).start();
    }



}
