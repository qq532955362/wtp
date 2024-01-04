package com.wtp.common.myjuc.concurrent;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomExecutor extends ThreadPoolExecutor {

    public CustomExecutor() {
        //5 core 15max 15s存活 50容量队列 非守护线程 满了直接拒绝
        this(5, 15, 15, TimeUnit.SECONDS, new LinkedBlockingDeque<>(50), ThreadFactoryBuilder.create().setDaemon(false).setNamePrefix("[wtp-customer]").build(), new ThreadPoolExecutor.AbortPolicy());
    }

    public CustomExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
}
