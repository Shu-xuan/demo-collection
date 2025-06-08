package com.hhy.thread.pool.util.cdl;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 15:03
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        System.out.println("现在21:00该下班了...");
        new Thread(()->{
           try {
               // 一直阻塞直到count为0
               countDownLatch.await();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
            System.out.println("同事们走光了，hhy终于可以走了...");
        }).start();
        for (int i=0;i<5;i++) {
            new Thread(()->{
                System.out.println("员工xxx下班了");
                countDownLatch.countDown();
            }).start();
        }
    }
}
