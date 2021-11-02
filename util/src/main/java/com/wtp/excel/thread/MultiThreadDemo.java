package com.wtp.excel.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wtp
 */


public class MultiThreadDemo {

    private static final AtomicInteger FLAG = new AtomicInteger(1);

    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    private static final int PRINT_COUNT = 50;

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(3, 5, 20, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(50), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        hangOnThread();
    }

    static void hangOnThread() {
        EXECUTOR.execute(MultiThreadDemo::run);
        EXECUTOR.execute(MultiThreadDemo::run2);
        EXECUTOR.execute(MultiThreadDemo::run3);
        EXECUTOR.execute(MultiThreadDemo::run);
        EXECUTOR.execute(MultiThreadDemo::run2);
        EXECUTOR.execute(MultiThreadDemo::run3);
        EXECUTOR.shutdown();
    }

    private static void run() {
        while (FLAG.get() <= PRINT_COUNT) {
            REENTRANT_LOCK.lock();
            try {
                if (FLAG.get() % 3 == 0) {
                    System.out.println(Thread.currentThread().getName() + "\t" + FLAG);
                    FLAG.getAndIncrement();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                REENTRANT_LOCK.unlock();
            }
        }
    }

    private static void run2() {
        while (FLAG.get() <= PRINT_COUNT) {
            REENTRANT_LOCK.lock();
            try {
                if (FLAG.get() % 3 == 1) {
                    System.out.println(Thread.currentThread().getName() + "\t" + FLAG);
                    FLAG.getAndIncrement();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                REENTRANT_LOCK.unlock();
            }
        }
    }

    private static void run3() {
        while (FLAG.get() <= PRINT_COUNT) {
            REENTRANT_LOCK.lock();
            try {
                if (FLAG.get() % 3 == 2) {
                    System.out.println(Thread.currentThread().getName() + "\t" + FLAG);
                    FLAG.getAndIncrement();
                    Thread.sleep(1000);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                REENTRANT_LOCK.unlock();
            }
        }
    }
}
