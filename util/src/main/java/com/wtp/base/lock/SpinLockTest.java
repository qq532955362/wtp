package com.wtp.base.lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockTest {
    private AtomicBoolean available = new AtomicBoolean(false);

    public void lock() {
        // 循环检测尝试获取锁
        while (!tryLock()) {
            // doSomething...
        }
    }

    public boolean tryLock() {
        // 尝试获取锁，成功返回true，失败返回false
        return available.compareAndSet(false, true);
    }

    public void unLock() {
        if (!available.compareAndSet(true, false)) {
            throw new RuntimeException("释放锁失败");
        }
    }
}
