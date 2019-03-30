package com.nemo.concurrent.tb7;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 线程间数据交互
 */
public class Demo {

    public void a(Exchanger<String> exchanger) {
        System.out.println("a 方法执行...");

        try {
            System.out.println("a 方法正在抓取数据...");
            Thread.sleep(2000);
            System.out.println("a 方法抓取到数据...");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String res = "123";

        try {
            System.out.println("a 等待对比结果...");
            //这里会一直等待 直到b也执行exchange
            String value = exchanger.exchange(res);
            System.out.println("交换结束 " + value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(Exchanger<String> exchanger) {
        System.out.println("b 方法开始执行...");

        try {
            System.out.println("b 方法正在抓取数据...");
            Thread.sleep(4000);
            System.out.println("b 方法抓取到数据...");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String res = "12345";

        try {
            //value就是交换过来的数据
            String value = exchanger.exchange(res);
            System.out.println("交换结束 " + value);
            System.out.println("开始进行比对,,,");
            System.out.println("比对结果为：" + value.equals(res));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> d.a(exchanger)).start();
        new Thread(() -> d.b(exchanger)).start();
    }
}
