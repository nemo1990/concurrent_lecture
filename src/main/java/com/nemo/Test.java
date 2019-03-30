package com.nemo;

public class Test {

    private static Object object = new Object();


    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            new Print(i, object).start();
        }
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