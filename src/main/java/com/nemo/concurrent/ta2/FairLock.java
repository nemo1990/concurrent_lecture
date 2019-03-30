package com.nemo.concurrent.ta2;

import java.util.ArrayList;
import java.util.List;

public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            waitingThreads.add(queueObject);
        }

        //在队列中的线程被唤醒后需要重新执行
        while (isLockedForThisThread) {
            synchronized (this) {
                //保证必须是队头第一个线程唤醒 并将其移出队列，指定为当前可执行线程
                isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
                if(!isLockedForThisThread) {
                    isLocked = true;
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                    return;
                }
            }
            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
                throw e;
            }
        }
    }

    public synchronized void unlock() {
        //释放锁的必须是当前线程，当前线程并没有在等待队列中
        if(this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if(waitingThreads.size() > 0) {
            //唤醒队头第一个线程
            waitingThreads.get(0).doNotify();
        }
    }
}
