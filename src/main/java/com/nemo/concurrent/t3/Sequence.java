package com.nemo.concurrent.t3;

public class Sequence {

    private int value;

    //放在普通方法上，内置锁就是当前类的实例
    public synchronized int getNext() {
        return value ++;
    }

    //修饰静态方法，内置锁是当前的Class字节码对象
    public static synchronized int getPrevious() {
//        return value --;
        return 0;
    }

    //代码块
    public int xx() {

        //monitorenter
        synchronized (Sequence.class) {
            if (value > 0) {
                return value;
            } else {
                return  -1;
            }
        }
        //monitorexit
    }

    public static void main(String[] args) {

        Sequence s = new Sequence();
//        while (true) {
//            System.out.println(s.getNext());
//        }

        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }


}
