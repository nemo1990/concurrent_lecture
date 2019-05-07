package com.nemo.concurrent.unsafe;

public class User {

    private static String title = "haha";

    private String name;
    private int age;

    private short a;

    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public short getA() {
        return a;
    }

    public void setA(short a) {
        this.a = a;
    }

    public static String getTitle() {
        return title;
    }
}
