package com.nemo.concurrent.tb8;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Demo {

    /**
     * Callable和Runnable的区别
     * Runnable是被线程调用的，run方法是异步执行的
     * Callable的call方法是由FutureTask的run方法调用的
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        Callable<Integer> call = () -> {
            Thread.sleep(3000);
            return 1;
        };

        FutureTask<Integer> task = new FutureTask<>(call);

        Thread thread = new Thread(task);
        thread.start();

        //do something
        System.out.println(" 干点别的...");

        Integer result = task.get();
        System.out.println("拿到的结果为：" + result);
    }
}
