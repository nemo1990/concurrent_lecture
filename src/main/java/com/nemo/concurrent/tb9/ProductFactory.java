package com.nemo.concurrent.tb9;

import com.nemo.producerAndConsumer.Producer;

import java.util.Random;

public class ProductFactory {

    public Future createProduct(String name) {
        System.out.println("下单成功，你可以去上班了...");
        Future f = new Future(); //创建一个订单

        //生产产品
        new Thread(() -> {
            Product p = new Product(new Random().nextInt(), name);
            f.setProduct(p);
        }).start();

        return f;
    }


}
