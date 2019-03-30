package com.nemo.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        Producer pro = new Producer(list);
        Consumer con = new Consumer(list);

        for(int i = 0; i <= 10; i++) {
            new Thread(pro).start();
            new Thread(con).start();
        }
    }

}
