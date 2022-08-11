package com.wtp.base.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AtomicCounter {
    private AtomicInteger integer = new AtomicInteger();

    public AtomicInteger getInteger() {
        return integer;
    }

    public void setInteger(AtomicInteger integer) {
        this.integer = integer;
    }

    public void increment() {
        integer.incrementAndGet();
    }

    public void decrement() {
        integer.decrementAndGet();
    }
}

class AtomicProducer extends Thread {
    private AtomicCounter atomicCounter;

    public AtomicProducer(AtomicCounter atomicCounter) {
        this.atomicCounter = atomicCounter;
    }

    @Override
    public void run() {
        for (int j = 0; j < AtomicTest.LOOP; j++) {
            System.out.println("producer : " + atomicCounter.getInteger());
            atomicCounter.increment();
        }
    }
}

class AtomicConsumer extends Thread {
    private AtomicCounter atomicCounter;

    public AtomicConsumer(AtomicCounter atomicCounter) {
        this.atomicCounter = atomicCounter;
    }

    @Override
    public void run() {
        for (int j = 0; j < AtomicTest.LOOP; j++) {
            System.out.println("consumer : " + atomicCounter.getInteger());
            atomicCounter.decrement();
        }
    }
}

class AtomicTest {
    final static int LOOP = 10;

    public static void main(String[] args) throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();
        AtomicProducer producer = new AtomicProducer(counter);
        AtomicConsumer consumer = new AtomicConsumer(counter);
        producer.start();
        consumer.start();
        //等待这两个线程执行完再输出
        producer.join();
        consumer.join();
        System.out.println(counter.getInteger());

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantLock reentrantLock = new ReentrantLock();
    }

}


