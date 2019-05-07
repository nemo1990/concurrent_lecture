package com.nemo.java8;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;

public class Test1 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

//        for(int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//
//        System.out.println("---------");
//
//        for(int i : list) {
//            System.out.println(i);
//        }
//
//        System.out.println("---------");

        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

//        list.forEach(integer -> System.out.println(integer));

        PrintStream printOut = System.out;
        list.forEach(printOut::println);

        Iterator<Integer> iterator = list.iterator();


        FutureTask<String> futureTask = new FutureTask<>("a"::toUpperCase);


    }



}
