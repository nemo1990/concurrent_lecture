package com.nemo.concurrent.t5;

public class Singleton {

    private Singleton() {}

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    //多线程环境下
    //必须有共享资源
    //对资源进行非原子性操作


}
