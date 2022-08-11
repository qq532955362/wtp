package com.wtp.base.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentryLockLock {

    private synchronized void doSomething() {
        System.out.println("doSomething...");
        doSomethingElse();
    }

    private synchronized void doSomethingElse() {
        System.out.println("doSomethingElse...");
    }


    @Test
    public void testSyn() {
        doSomething();
    }


    private static final ReentrantLock reentrantLock = new ReentrantLock();


    public static void outMethod() {
        reentrantLock.lock();
        System.out.println("holdCount - out = " + reentrantLock.getHoldCount());
        inMethod();
        reentrantLock.unlock();
    }

    public static void inMethod() {
        reentrantLock.lock();
        reentrantLock.lock();
        int holdCount = reentrantLock.getHoldCount();
        System.out.println("holdCount - in = " + holdCount);
        System.out.println(Thread.currentThread().getName());
        reentrantLock.unlock();
        reentrantLock.unlock();
    }

    @Test
    public void testLock() {
//        for (int i = 0; i < 10; i++) {
//            new Thread(this::outMethod).start();
//        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> outMethod(),"线程"+ String.valueOf(i)).start();
        }
    }

}
