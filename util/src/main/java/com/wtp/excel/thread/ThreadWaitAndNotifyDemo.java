package com.wtp.excel.thread;

/**
 * @author wangtaiping
 * 2021/11/22 17:14
 */
public class ThreadWaitAndNotifyDemo {

    public static void main(String[] args) {
        Object o = new Object();

        //传入同一个监视器对象
        ThreadDemo threadDemo = new ThreadDemo(o);
        Thread thread1 = new Thread(threadDemo);
        thread1.setName("线程1");

        Thread thread2 = new Thread(threadDemo);
        thread2.setName("线程2");

        thread1.start();
        thread2.start();
    }
}


class ThreadDemo implements Runnable {

    //监视器对象
    private Object o;

    private int i = 1;

    public ThreadDemo(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        while (i <= 100) {
            //同步块 以构造方法传入的监视器对象
            synchronized (o) {
                //
                o.notify();
                o.notifyAll();
                if (i <= 100) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                    i++;
                    try {
                        if (i > 100) {
                            break;
                        } else {
                            o.wait();
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
        }
    }
}