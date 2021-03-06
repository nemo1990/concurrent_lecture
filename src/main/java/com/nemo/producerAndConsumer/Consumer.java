package com.nemo.producerAndConsumer;

import java.util.List;

public class Consumer implements Runnable{

    private List<Product> list;

    public Consumer(List<Product> list) {
        this.list = list;
    }

    @Override
    public void run() {
        if(list.size() == 0) {
            try {
                System.out.println("产品被全部消费，等待生产者生产！");

                synchronized (list) {
                    list.wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("消费者消费一个产品");
            list.remove(0);

            synchronized (list) {
                list.notifyAll();
            }
        }
    }
}
