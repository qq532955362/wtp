package com.wtp.base.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicReference;

public class UnReenTryLock {

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        //这句是很经典的“自旋”语法，AtomicInteger中也有
        for (;;) {
            if (!owner.compareAndSet(null, current)) {
                return;
            }
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
    }

    public void outMethod(){
        lock();
        lock();
        System.out.println("Out");
        inMethod();
        unlock();
    }

    public void inMethod() {
        lock();
        System.out.println("in");
    }

    @Test
    public void test(){
    }
}
