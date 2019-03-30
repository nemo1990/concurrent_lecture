package com.nemo.concurrent.tb1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue<E> {

    private Object[] obj;

    private int addIndex;
    private int removeIndex;
    private int queueSize;

    private Lock lock = new ReentrantLock();
    Condition addCondition = lock.newCondition();
    Condition removeCondition = lock.newCondition();

    public MyQueue(int count) {
        obj = new Object[count];
    }

    public void add(E e) {
        lock.lock();
        while (queueSize == obj.length) {
            try {
                System.out.println(Thread.currentThread().getName() + " 满了 等待");
                addCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        obj[addIndex] = e;

        System.out.println(Thread.currentThread().getName() + " 添加一个元素");
        addIndex ++;
        addIndex %= obj.length;

        queueSize++;
        removeCondition.signal();
        lock.unlock();
    }

    public void remove() {
        lock.lock();
        while (queueSize == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 队列为空，不进行移除");
                removeCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        obj[removeIndex] = null;
        System.out.println(Thread.currentThread().getName() + " 移除一个元素");

        removeIndex ++;
        removeIndex %= obj.length;

        queueSize--;
        addCondition.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>(10);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(1);
            }
        }).start();

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.remove();
            }
        }).start();

    }
}
