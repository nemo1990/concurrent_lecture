package com.nemo.concurrent.volitale;

public class Task implements Runnable{

    //没加volatile会一直死循环
    volatile boolean running = true;
    int i = 0;

    @Override
    public void run() {
        while (running) {
            i ++;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Task task = new Task();
        Thread th = new Thread(task);
        th.start();
        Thread.sleep(10);
        task.running = false;
        Thread.sleep(100);
        System.out.println(task.i);
        System.out.println(task.running);
        System.out.println("程序停止");
    }
}
