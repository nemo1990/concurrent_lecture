package com.nemo.java8.stream2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyComparatorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");

        //字母升序
//        Collections.sort(list);

        //长度升序
//        Collections.sort(list, (item1, item2) -> item1.length() - item2.length());

        //长度降序
//        Collections.sort(list, (item1, item2) -> item2.length() - item1.length());

        //使用reversed从升序到降序
//        Collections.sort(list, Comparator.comparingInt(String::length).reversed());

        //必须指定item的类型 否则编译器会看做Object 原因是reversed造成的类型不明确
//        Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());

//        list.sort(Comparator.comparingInt(String::length).reversed());
//        list.sort(Comparator.comparingInt((String item) -> item.length()).reversed());

//        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));

//        Collections.sort(list, Comparator.comparingInt(String::length)
//                .thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));

//        Collections.sort(list, Comparator.comparingInt(String::length)
//                .thenComparing(Comparator.comparing(String::toLowerCase)));

        //先长度排序 再小写的逆序
//        Collections.sort(list, Comparator.comparingInt(String::length)
//                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));

//        Collections.sort(list, Comparator.comparingInt(String::length).reversed()
//                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));

        //最后一个comparator没有发挥作用 第二个已经返回了
        Collections.sort(list, Comparator.comparingInt(String::length).reversed()
                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder()))
                .thenComparing(Comparator.reverseOrder()));


        System.out.println(list);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());

        pairs.forEach(arr -> System.out.println(arr[0] + " " + arr[1]));
    }
}
