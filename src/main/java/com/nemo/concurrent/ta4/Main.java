package com.nemo.concurrent.ta4;

public class Main {

    private int value;
    private MyLock2 lock = new MyLock2();

    public int next() {
        lock.lock();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
        return value ++;
    }

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
//        Main m = new Main();

//        for(int i = 0; i < 3; i++) {
//            new Thread(() -> {
//                while (true) {
//                    System.out.println(Thread.currentThread().getId() + " " + m.next());
//                }
//            }).start();
//        }

//        new Thread(() -> m.a()).start();

        byte c = (byte) 120;
        System.out.println(c >> 7);


    }
}
