package com.wtp.base.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketLock {
    // 队列票据(当前排队号码)
    private AtomicInteger queueNum = new AtomicInteger();
    // 出队票据(当前需等待号码)
    private AtomicInteger dequeueNum = new AtomicInteger();

    // 获取锁：如果获取成功，返回当前线程的排队号
    public int lock() {
        int currentTicketNum = dequeueNum.incrementAndGet();
        while (currentTicketNum != queueNum.get()) {
            // doSomething...
        }
        return currentTicketNum;
    }

    // 释放锁：传入当前排队的号码
    public void unLock(int ticketNum) {
        queueNum.compareAndSet(ticketNum, ticketNum + 1);
    }
}

