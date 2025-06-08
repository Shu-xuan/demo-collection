package com.hhy.thread.pool.reentrant;

import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 描述: 可重入锁
 * </p>
 *
 * @Author huhongyuan
 */
public class RELock {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        new Thread(()->{
            lock.lock();
        }).start();
    }
}
