package com.nemo.concurrent.ta1;

public class Sequence {

    private MyLock lock = new MyLock();

    private int value;

    public int getNext() {
        lock.lock();
        value ++;
        lock.unlock();
        return value;
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();

        for(int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(s.getNext());
                }
            }).start();
        }
    }
}
