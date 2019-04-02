package com.nemo.concurrent.unsafe;

import sun.misc.Unsafe;

public class RevisedObjectInHeap {

    private long address = 0;

    private Unsafe unsafe = GetUnsafeInstance.getUnsafeInstance();

    //让对象占用堆内存，出发Full GC
    private byte[] bytes = null;

    public RevisedObjectInHeap() {
        address = unsafe.allocateMemory(2 * 1024 * 1024);
        bytes = new byte[1024 * 1024];
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize." + bytes.length);
        unsafe.freeMemory(address);
    }

    public static void main(String[] args) {
        while (true) {
            RevisedObjectInHeap heap = new RevisedObjectInHeap();
            System.out.println("memory address = " + heap.address);
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
