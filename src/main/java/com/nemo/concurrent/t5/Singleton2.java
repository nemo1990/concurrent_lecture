package com.nemo.concurrent.t5;

public class Singleton2 {

    private Singleton2() {}

    private static volatile Singleton2 instance;

    /**
     * 双重检查加锁
     * @return
     */
    public static Singleton2 getInstance() {
        //自旋 while(true)
        if(instance == null) {
            synchronized (Singleton2.class) {
                if(instance == null) {
                    instance = new Singleton2(); //指令重排序

                    //申请一块内存空间
                    //在这块空间里实例化对象
                    //instance的引用指向这块空间地址
                }
            }
        }
        return instance;
    }
}
