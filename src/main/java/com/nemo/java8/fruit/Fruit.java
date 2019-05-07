package com.nemo.java8.fruit;

public interface Fruit {

    void test();

    default void look() {
        System.out.println("ddd");
    }
}
