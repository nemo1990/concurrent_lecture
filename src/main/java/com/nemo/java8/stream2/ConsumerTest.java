package com.nemo.java8.stream2;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ConsumerTest {

//    public void test(Consumer<Integer> consumer) {
//        if(consumer instanceof IntConsumer) {
//            System.out.println("传了个IntConsumer进来");
//        }
//        consumer.accept(100);
//    }

    public void test(IntConsumer consumer) {
        consumer.accept(100);
    }

    public static void main(String[] args) {
        ConsumerTest consumerTest = new ConsumerTest();

        Consumer<Integer> consumer = i -> System.out.println(i);
        IntConsumer intConsumer = i -> System.out.println(i);

//        System.out.println(consumer instanceof Consumer);
//        System.out.println(intConsumer instanceof IntConsumer);

//        consumerTest.test(consumer);//面向对象方式

        consumerTest.test((IntConsumer) consumer::accept);//函数式方式
//        consumerTest.test(intConsumer::accept);//函数式方式
        //两者行为一致
//        consumerTest.test(t -> System.out.println(t));

    }



}
