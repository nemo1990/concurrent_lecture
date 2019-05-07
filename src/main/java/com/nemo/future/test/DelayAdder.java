package com.nemo.future.test;

import com.nemo.future.core.IFuture;

//延迟加法
public class DelayAdder {

    public static void main(String[] args) throws Exception {
        IFuture f = new DelayAdder().add(3 * 1000, 1, 2);
//        Thread.sleep(4000);
        f.addListener(future -> {
            System.out.println("监听器被调用 " + Thread.currentThread().getName());
            System.out.println(future.get());
        });

//        f.get();
//        System.out.println("立即执行了吗？" + Thread.currentThread().getName());
    }

    /**
     * 延迟加
     * @param delay 延迟时长 milliseconds
     * @param a 加数
     * @param b 加数
     * @return 异步结果
     */
    public DelayAdditionFuture add(long delay, int a, int b) {
        DelayAdditionFuture future = new DelayAdditionFuture();
        new Thread(new DelayAdditionTask(delay, a, b, future)).start();
        return future;
    }

    private class DelayAdditionTask implements Runnable {

        private long delay;

        private int a, b;

        private DelayAdditionFuture future;

//        private Thread callThread;

        public DelayAdditionTask(long delay, int a, int b, DelayAdditionFuture future) {
            super();
            this.delay = delay;
            this.a = a;
            this.b = b;
            this.future = future;
//            this.callThread = Thread.currentThread();
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delay);
                Integer i = a + b;
                //这里设置future为正常执行完成状态
//                System.out.println("调用方线程为：" + callThread.getName());
                future.setSuccess(i);
                System.out.println("逻辑线程完毕：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                //这里设置future为异常执行完成状态
                future.setFailure(e.getCause());
            }
        }
    }

}
