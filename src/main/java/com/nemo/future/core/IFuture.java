package com.nemo.future.core;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public interface IFuture<V> extends Future<V>{

    //是否成功
    boolean isSuccess();

    //立即返回，不管Future是否处于完成状态
    V getNow();

    //若执行失败时的原因
    Throwable cause();

    //是否可以取消
    boolean isCancellable();

    //等待Future的完成
    IFuture<V> await() throws InterruptedException;

    //超时等待Future完成
    boolean await(long timeoutMillis) throws InterruptedException;

    boolean await(long timeoutMillis, TimeUnit timeUnit) throws InterruptedException;

    //等待Future完成，不响应中断
    IFuture<V> awaitUninterruptibly();

    //超时等待Future完成，不响应中断
    boolean awaitUninterruptibly(long timeoutMillis);

    boolean awaitUninterruptibly(long timeout, TimeUnit timeUnit);

    //当Future完成时，会通知这些加进来的监听器
    IFuture<V> addListener(IFutureListener<V> l);

    IFuture<V> removeListener(IFutureListener<V> l);
}
