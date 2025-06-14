package com.hhy.thread;

import com.hhy.thread.handler.impl.DiscardRejectHandler;
import com.hhy.thread.handler.impl.ThrowRejectHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("本机核心线程数为: " + Runtime.getRuntime().availableProcessors());
//        fixedSingle();
        multiThreadPool();
    }

    private static void multiThreadPool() {
        MultiThreadPool myThreadPool = new MultiThreadPool(4,8,new ArrayBlockingQueue<>(64), 500, TimeUnit.MILLISECONDS, new DiscardRejectHandler());
        for (int i = 0; i < 512; i++) {
            myThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println("主线程正常执行...");
    }

    private static void fixedSingle() {
        FixedSingleThreadPool myThreadPool = new FixedSingleThreadPool();
        for (int i = 0; i < 10; i++) {
            myThreadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println("主线程正常执行...");
    }

}
