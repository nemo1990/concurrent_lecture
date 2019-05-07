package com.nemo.future.core;

public interface IFutureListener<V> {

    void operationCompleted(IFuture<V> future) throws Exception;
}
