package com.nemo.java8.defaultmethod;

public class MyClass implements MyInterface1, MyInterface2{

    @Override
    public void myMethod() {
        MyInterface2.super.myMethod();
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();
    }
}
