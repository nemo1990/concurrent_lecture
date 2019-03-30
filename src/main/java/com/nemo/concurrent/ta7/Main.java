package com.nemo.concurrent.ta7;

public class Main {

    public static void main(String[] args) {
        Tmall2 tmall = new Tmall2();

        PushTarget p = new PushTarget(tmall);
        TakeTarget t = new TakeTarget(tmall);

        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();

        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();

    }
}
