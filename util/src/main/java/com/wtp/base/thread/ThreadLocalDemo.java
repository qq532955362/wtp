package com.wtp.base.thread;

/**
 * @author wangtaiping
 * 2021/12/9 17:28
 */
public class ThreadLocalDemo {

    /**
     * 每个线程都会持有ThreadLocal对象的一个副本，所以每个线程操作的实际上是该线程内的变量不存在线程间通信一说
     */
    static ThreadLocal<String> threadLocal = new ThreadLocal();

    /**
     * 打印当前线程的threadLocal值并移除
     *
     * @param threadName
     */
    static void printVar(String threadName) {
        System.out.println(threadName + "\t" + threadLocal.get());
    }

    public static void main(String[] args) {

        new Thread(() -> {
            threadLocal.set("1");
            printVar(Thread.currentThread().getName());
        }).start();

        new Thread(() -> {
            threadLocal.set("2");
            printVar(Thread.currentThread().getName());
        }).start();
    }

}
