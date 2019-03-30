package com.nemo.concurrent.tc3;

public class Demo {

    private int a;
    private int b;
    private int c;

    /**
     * 3 happens-before 2
     * 4 happens-before 3
     */
    public void a() {
        a = 2; //1
        b = 10; //2

        c = a + b; //3
        System.out.println(c); //4
    }

}
