package com.nemo.concurrent.tc2;

public class Demo2 {

    private int a;
    private boolean flag;

    public void write() {
        //这两个数据之间没有依赖，因此处理器会对这两行代码进行指令重排序
        a = 1;
        flag = true;
    }

    public void read() {
        if(flag) {
            int b = a + 1;
            System.out.println(b);
        }
    }

}
