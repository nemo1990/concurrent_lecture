package com.nemo.java8.stream2;


import java.util.function.Consumer;

public class LambdaTest {

    Runnable r1 = () -> System.out.println(this);

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    Consumer<Integer> c1 = i -> System.out.println(this);

    Consumer<Integer> c2 = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) {
            System.out.println(this);
        }
    };

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();

        Thread t1 = new Thread(lambdaTest.r1);
        t1.start();

        System.out.println("--------------");

        Thread t2 = new Thread(lambdaTest.r2);
        t2.start();

//        lambdaTest.c1.accept(1);
//        lambdaTest.c2.accept(1);
    }


}
