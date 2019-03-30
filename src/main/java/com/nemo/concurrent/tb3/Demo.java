package com.nemo.concurrent.tb3;

public class Demo {

    //每个线程独有
    private ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

    public int getNext() {
        Integer value = count.get();
        value ++;
        count.set(value);
        return value;
    }

    public static void main(String[] args) {
        Demo d = new Demo();

        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + d.getNext());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
    }
}
