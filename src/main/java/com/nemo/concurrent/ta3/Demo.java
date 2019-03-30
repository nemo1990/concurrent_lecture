package com.nemo.concurrent.ta3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Demo {

    private Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private Lock r = rwl.readLock();
    private Lock w = rwl.writeLock();

    public Object get(String key) {
        r.lock();
        System.out.println(Thread.currentThread().getName() + " 读操作在执...");
        try {
            Thread.sleep(3000);
            return map.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            r.unlock();
            System.out.println(Thread.currentThread().getName() + " 读操作执行完毕...");
        }
    }

    public void put(String key, Object value) {
        w.lock();
        System.out.println(Thread.currentThread().getName() + " 写操作在执...");
        try {
            Thread.sleep(3000);
            map.put(key, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + " 写操作执行完毕...");
        }
    }
}
