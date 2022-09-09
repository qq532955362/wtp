package com.wtp.common.myjuc.concurrent;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyCompletableFutureTest {

    private final static Logger log = LoggerFactory.getLogger(MyCompletableFutureTest.class);
    private final static Executor customerExecutor = new ThreadPoolExecutor(5,
            10,
            15,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            ThreadFactoryBuilder.create().setNamePrefix("[wtp-customer]").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Test
    public void testCompletableFuture() {

        //sleep 4s
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
            try {
                log.debug("4s-----start");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                log.debug("error: " + e.getMessage());
            }
            log.debug("4s-----end");
            return "4s结束";
        }, customerExecutor);
        f1.exceptionally((a)->{
            String message = a.getMessage();
            log.error("异常信息:{}",message,a);
            return message;
        });

        //sleep 3s
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
            try {
                log.debug("3s-----start");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.debug("error",e);
            }
            log.debug("3s-----end");
            throw new IllegalStateException("3s-----startError");
        }, customerExecutor);
        f2.exceptionally((a)->{
            String message = a.getMessage();
            log.error("异常信息:{}",message,a);
            throw new RuntimeException(message);
        });

    }

}