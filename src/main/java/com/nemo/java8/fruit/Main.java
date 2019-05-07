package com.nemo.java8.fruit;

public class Main {

    private void eat(Fruit fruit) {
        fruit.test();
    }


    public static void main(String[] args) {
        Main main = new Main();

        main.eat(() -> System.out.println("好吃"));



    }
}
