package com.nemo.concurrent.tb9;

public class Future {

    private Product product;

    private volatile boolean down;

    public synchronized void setProduct(Product product) {
        if(down) {
            return;
        }
        this.product = product;
        down = true;
        notify();
    }

    public synchronized Product get() {

        while (!down) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return product;
    }

}
