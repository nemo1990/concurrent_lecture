package com.nemo.concurrent.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

        DemoService ds = ac.getBean(DemoService.class);

        ds.a();
        ds.b();
    }
}
