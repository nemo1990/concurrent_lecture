package com.nemo.java8.optional;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {

    public static void main(String[] args) {
        String a = "hello123, world";
        Optional<String> optional = Optional.ofNullable(a);

        optional.ifPresent(item -> System.out.println(item)); //推荐的Optional使用方式

//        optional.ifPresent(s -> System.out.println(s));


        System.out.println("-------");

        System.out.println(optional.orElse("world"));
        System.out.println("-------");

        System.out.println(optional.orElseGet(() -> "nihao"));

    }
}
