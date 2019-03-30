package com.nemo.concurrent.tc6;

public class Demo2 {

    private final Object obj;

    public Demo2() {
        obj = new Object();
    }

    private Demo2 demo;

    public void w() {
        demo = new Demo2();
    }

    public void r() {
        Demo2 d = demo;
    }





}
