package com.wtp.common.myjuc.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class MyCompletableFuture {

    private final static Logger log = LoggerFactory.getLogger(MyCompletableFuture.class);

    private final static ThreadPoolExecutor customThreadPool = new CustomExecutor();

    public static void main(String[] args) {

        /**
         * 自定义的线程池中的线程由ThreadFactory产出的时候默认设置的Daemon是false也就是说自定义的线程池是**用户线程**（除非你改动了工厂的设置）
         * 单元测试JUnit框架会把我们创建的线程设置为守护线程()
         */

        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                log.info("执行中");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, customThreadPool);

        for (int i = 0; i < 10; i++) {
            log.info("执行zhong");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
