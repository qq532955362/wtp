package com.wtp.common.myjuc.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;

public class MyCyclicBarrier {

    private static final ThreadPoolExecutor customExecutor = new CustomExecutor();

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            customExecutor.submit(new TestTask(cyclicBarrier));
        }
        customExecutor.shutdown();
    }

    static class TestTask implements Runnable {

        private final CyclicBarrier cyclicBarrier;

        public TestTask(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
