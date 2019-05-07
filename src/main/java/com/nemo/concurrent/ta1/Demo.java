package com.nemo.concurrent.ta1;

public class Demo {

    private MyLock lock = new MyLock();

    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo d = new Demo();

        new Thread(() -> d.a()).start();


    }
}
