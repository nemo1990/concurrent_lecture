package com.nemo.concurrent.unsafe;

import sun.misc.Unsafe;

public class ObjectInHeap {

    private long address = 0;

    private Unsafe unsafe = GetUnsafeInstance.getUnsafeInstance();

    public ObjectInHeap() {
        address = unsafe.allocateMemory(2 * 1024 * 1024);
    }

    public static void main(String[] args) {
        //会抛出OutOfMemoryError，使用Unsafe获取的堆外内存必须由程序显式的释放，JVM不会帮助我们做这件事情
        while (true) {
            ObjectInHeap heap = new ObjectInHeap();
            System.out.println("memory address = " + heap.address);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
