package com.nemo.future.core;

import java.util.Collection;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AbstractFuture<V> implements IFuture<V> {

    //需要保证其可见性
    protected volatile Object result;

    //监听器集
    protected Collection<IFutureListener<V>> listeners = new CopyOnWriteArrayList<>();

    //当任务正常执行结果为null时，即客户端调用setSuccess(null)时，result引用该对象
    private static final SuccessSignal SUCCESS_SIGNAL = new SuccessSignal();

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        //已经完成不能取消
        if(isDone()) {
            return false;
        }

        synchronized (this) {
            //双重检测
            if(isDone()) {
                return false;
            }

            result = new CauseHolder(new CancellationException());
            notifyAll(); //isDone = true，通知等待在该对象的wait()的线程
        }
        notifyListeners(); //通知监听器该异步操作已完成
        return true;
    }

    @Override
    public boolean isCancellable() {
        return result == null;
    }

    @Override
    public boolean isCancelled() {
        return result != null && result instanceof CauseHolder
                && ((CauseHolder)result).cause instanceof CancellationException;
    }

    @Override
    public boolean isDone() {
        return result != null;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        System.out.println("进行获取结果 " + Thread.currentThread().getName());
        await(); //等待执行结果

        Throwable cause = cause();
        if(cause == null) { //没有发生异常，异步操作正常结束
            return getNow();
        }
        if(cause instanceof CancellationException) { //异常操作被取消了
            throw (CancellationException)cause;
        }
        throw new RuntimeException(cause); //其他异常
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if(await(timeout, unit)) { //超时等待执行结果
            Throwable cause = cause();
            if(cause == null) { //没有发生异常，异步操作正常结束
                return getNow();
            }
            if(cause instanceof CancellationException) { //异常操作被取消了
                throw (CancellationException)cause;
            }
            throw new RuntimeException(cause); //其他异常
        }
        //时间到了异步操作还没有结束，抛出超时异常
        throw new TimeoutException();
    }

    @Override
    public boolean isSuccess() {
        return result == null ? false : !(result instanceof CauseHolder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public V getNow() {
        return (V)(result == SUCCESS_SIGNAL ? null : result);
    }

    @Override
    public Throwable cause() {
        if(result != null && result instanceof CauseHolder) {
            return ((CauseHolder)result).cause;
        }
        return null;
    }

    @Override
    public IFuture<V> addListener(IFutureListener<V> listener) {
        if(listener == null) {
            throw new NullPointerException("listener");
        }
        if(isDone()) { //若已完成直接通知该监听器
            notifyListener(listener);
            return this;
        }
        synchronized (this) {
            if(!isDone()) {
                listeners.add(listener);
                return this;
            }
        }
        notifyListener(listener);
        return this;
    }

    @Override
    public IFuture<V> removeListener(IFutureListener<V> listener) {
        if(listener == null) {
            throw new NullPointerException("listener");
        }
        if(!isDone()) {
            listeners.remove(listener);
        }

        return this;
    }

    @Override
    public IFuture<V> await() throws InterruptedException {
        return await0(true);
    }

    private IFuture<V> await0(boolean interruptable) throws InterruptedException {
        //若已完成就直接返回了
        if(!isDone()) {
            //若允许中断而且被中断了则抛出中断异常
            if(interruptable && Thread.interrupted()) {
                throw new InterruptedException("thread" + Thread.currentThread().getName() + " has been interrupted.");
            }

            boolean interrupted = false;
            synchronized (this) {
                while (!isDone()) {
                    try {
                        wait(); //释放锁进入waiting状态，等待其他线程调用本对象的notify()/notifyAll()方法
                    } catch (InterruptedException e) {
                        if(interruptable) {
                            throw e;
                        } else {
                            interrupted = true;
                        }
                    }
                }
            }
            if(interrupted) {
                //wait方法返回后，中断标志是被clear了
                //重新设置以便让其他代码知道这里被中断了
                Thread.currentThread().interrupt();
            }
        }
        return this;
    }

    @Override
    public boolean await(long timeoutMillis) throws InterruptedException {
        return await0(TimeUnit.MICROSECONDS.toNanos(timeoutMillis), true);
    }

    @Override
    public boolean await(long timeoutMillis, TimeUnit timeUnit) throws InterruptedException {
        return await0(timeUnit.toNanos(timeoutMillis), true);
    }

    private boolean await0(long timeoutNanos, boolean interruptable) throws InterruptedException{
        if(isDone()) {
            return true;
        }

        if(timeoutNanos <= 0) {
            return isDone();
        }

        if(interruptable && Thread.interrupted()) {
            throw new InterruptedException(toString());
        }

        long startTime = timeoutNanos <= 0 ? 0 : System.nanoTime();
        long waitTime = timeoutNanos;
        boolean interrupted = false;

        try {
            synchronized (this) {
                if(isDone()) {
                    return true;
                }

                if(waitTime <= 0) {
                    return isDone();
                }

                for(;;){
                    try {
                        wait(waitTime / 1000000, (int)(waitTime % 1000000));
                    } catch (InterruptedException e) {
                        if (interruptable) {
                            throw e;
                        } else {
                            interrupted = true;
                        }
                    }

                    if (isDone()) {
                        return true;
                    } else {
                        waitTime = timeoutNanos - (System.nanoTime() - startTime);
                        if (waitTime <= 0) {
                            return isDone();
                        }
                    }
                }
            }
        } finally {
            if(interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public IFuture<V> awaitUninterruptibly() {
        try {
            return await0(false);
        } catch (InterruptedException e) {
            throw new java.lang.InternalError();
        }
    }

    @Override
    public boolean awaitUninterruptibly(long timeoutMillis) {
        try {
            return await0(TimeUnit.MILLISECONDS.toNanos(timeoutMillis), false);
        } catch (InterruptedException e) {
            throw new java.lang.InternalError();
        }
    }

    @Override
    public boolean awaitUninterruptibly(long timeout, TimeUnit timeUnit) {
        try {
            return await0(timeUnit.toNanos(timeout), false);
        } catch (InterruptedException e) {
            throw new java.lang.InternalError();
        }
    }

    protected IFuture<V> setFailure(Throwable cause) {
        if(setFailure0(cause)) {
            notifyListeners();
            return this;
        }
        throw new IllegalStateException("complete already: " + this);
    }

    private boolean setFailure0(Throwable cause) {
        if(isDone()) {
            return false;
        }

        synchronized (this) {
            if(isDone()) {
                return false;
            }
            result = new CauseHolder(cause);
            notifyAll();
        }
        return true;
    }

    protected IFuture<V> setSuccess(Object result) {
        if(setSuccess0(result)) { //设置成功后通知监听器
            notifyListeners();
            return this;
        }
        throw new IllegalStateException("complete already: " + this);
    }

    private boolean setSuccess0(Object result) {
        if(isDone()) {
            return false;
        }

        synchronized (this) {
            if(isDone()) {
                return false;
            }
            if(result == null) { //异步操作正常执行完毕的结果是null
                this.result = SUCCESS_SIGNAL;
            } else {
                this.result = result;
            }
            System.out.println("设置成功操作 " + Thread.currentThread().getName());
            notifyAll();
        }
        return true;
    }

    private void notifyListeners() {
        for(IFutureListener<V> listener : listeners) {
            notifyListener(listener);
        }
    }

    private void notifyListener(IFutureListener<V> l) {
        try {
            l.operationCompleted(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class SuccessSignal {

    }

    private static class CauseHolder {
        final Throwable cause;

        CauseHolder(Throwable cause) {
            this.cause = cause;
        }
    }
}
