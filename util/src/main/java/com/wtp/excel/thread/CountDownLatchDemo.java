package com.wtp.excel.thread;

import com.wtp.excel.constant.IFunction;
import com.wtp.excel.constant.IModule;
import com.wtp.excel.constant.MyIFunction;
import com.wtp.excel.constant.MyIModule;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wtp
 * 2021/11/18 22:49
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new MyThreadFactory(MyIModule.TEST_MODULE, MyIFunction.TEST_FUNCTION));
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            Runnable runnable = () -> {
                try {
                    System.out.println("选手" + Thread.currentThread().getName() + "正在等待裁判发布口令");
                    cdOrder.await();
                    System.out.println("选手" + Thread.currentThread().getName() + "已接受裁判口令");
                    Thread.sleep((long) (1 * 10000));
                    System.out.println("选手" + Thread.currentThread().getName() + "到达终点");
                    cdAnswer.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (1 * 10000));
            System.out.println("裁判" + Thread.currentThread().getName() + "即将发布口令");
            cdOrder.countDown();
            System.out.println("裁判" + Thread.currentThread().getName() + "已发送口令，正在等待所有选手到达终点");
            cdAnswer.await();
            System.out.println("所有选手都到达终点");
            System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();


    }

    public static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger poolNumber = new AtomicInteger(1);

        private AtomicInteger threadCount = new AtomicInteger(1);

        private IModule module;

        private IFunction function;

        private ThreadGroup group;

        public MyThreadFactory(IModule module, IFunction function) {
            this.function = function;
            this.module = module;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                this.group = securityManager.getThreadGroup();
            }
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, module.getName() + "--" + function.getName() + "线程池" + poolNumber + "线程" + threadCount.getAndIncrement());
            thread.setPriority(10);
            return thread;
        }
    }

}
