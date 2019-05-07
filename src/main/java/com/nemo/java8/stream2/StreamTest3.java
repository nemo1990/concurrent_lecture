package com.nemo.java8.stream2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "welcome");
        list.stream().map(s -> s + "_a").filter(s -> s.length() > 3).forEach(System.out::println);
    }
}
