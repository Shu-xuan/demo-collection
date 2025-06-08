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
 * @since 2025/3/9 15:08
 */
public class CountDownLatchTest2 {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(()->{
            try {
                Thread.sleep(100);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hhy终于写完了");
        }).start();
        for (int i=0;i<5;i++) {
            new Thread(()->{
                System.out.println("同事们等待hhy的接口文档...");
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("hhy终于提交了，同事们可以开始工作了...");
            }).start();
        }
    }
}
