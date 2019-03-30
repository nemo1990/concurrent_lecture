package com.nemo.concurrent.t2;

public class Demo1 extends Thread{

    public Demo1(String name) {
        super(name);
    }


    @Override
    public void run() {
        while (!interrupted()) {
            System.out.println(getName() + "线程执行了...");
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo1 d1 = new Demo1("first-thread");
        Demo1 d2 = new Demo1("second-thread");

        //主线程的守护线程，随着主线程的结束而退出 必须在start之前进行设置
//        d1.setDaemon(true);
//        d2.setDaemon(true);

        d1.start();
        d2.start();

        Thread.sleep(1000);

        //--终止线程
        //stop不会释放锁 会造成问题
//        d1.stop();

        //比较推荐，只是设置个中断标志，需要手工逻辑去让线程终止
        d1.interrupt();
    }
}
