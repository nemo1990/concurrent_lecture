package com.nemo.java8;

import java.util.Objects;
import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args) {
        FunctionTest test = new FunctionTest();
//        System.out.println(test.compute(1, value -> {return 2 * value;}));
//        System.out.println(test.compute(2, value -> 5 + value));
//        System.out.println(test.compute(3, value -> value * value));
//
//        System.out.println(test.convert(5, value -> String.valueOf(value + "helloworld")));

//        Function<Integer, Integer> function = value -> value * 2;
//        System.out.println(test.compute(1, function));


        Function<Integer, Integer> function1 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        };

        Function<Integer, Integer> function2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        };

        System.out.println(function2.apply(function1.apply(5)));

    }

    public int compute(int a, Function<Integer, Integer> function) {
        int result = function.apply(a);
        return result;
    }

    public String convert(int a, Function<Integer, String> function) {
        return function.apply(a);
    }
}
