package com.wtp.base.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                8,
                10000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2));

        for (int i = 0; i <6; i++) {
            threadPoolExecutor.execute(()-> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        System.out.println("corePoolSize = " + corePoolSize);
        int size = threadPoolExecutor.getQueue().size();
        System.out.println("size = " + size);
        int maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();
        System.out.println("maximumPoolSize = " + maximumPoolSize);
        int largestPoolSize = threadPoolExecutor.getLargestPoolSize();
        System.out.println("largestPoolSize = " + largestPoolSize);
    }

}
