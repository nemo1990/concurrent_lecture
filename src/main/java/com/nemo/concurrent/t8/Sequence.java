package com.nemo.concurrent.t8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class Sequence {

    private AtomicInteger value = new AtomicInteger(0);

    private int[] s = {2, 1, 4, 6};
    AtomicIntegerArray a = new AtomicIntegerArray(s);

    //更新user
    AtomicReference<User> user = new AtomicReference<>();

    //变量必须是public volatile的
    AtomicIntegerFieldUpdater<User> old = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    public int getNext() {
        User user = new User();
        old.getAndIncrement(user);

        System.out.println(user.old);


        a.getAndIncrement(2);
        a.getAndAdd(2, 10);
        return value.getAndIncrement();
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " " + s.getNext())).start();
    }


}
