package com.nemo.concurrent.tc5;

public class Demo {

    private int a;
    private volatile boolean flag;

    public void write() {
        a = 1; // 1
        flag = true; //2  当写一个volatile变量时，Java模型会把该线程对应的本地内存中的共享变量值刷新到主内存中
    }

    public void read() {
        if(flag) {  //3 当读一个volatile变量时，Java内存模型会把当前线程对应的本地内存置为无效，从主内存中获取
            int b = a + 1;  //4
            System.out.println(b);  //5
        }
    }
}
