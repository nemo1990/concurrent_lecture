package com.nemo.concurrent.tc3;

public class Demo3 {

    public void a() {
        System.out.println("a"); //1 启动另外一个线程的线程
        new Thread(() -> {
            System.out.println("b"); //2  1 happens-before 2
        }).start();
    }
}
