package com.hhy.thread.pool.util.cyclic;

import java.time.LocalDateTime;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 15:21
 */
public class Test {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; ++i) {
            new Thread(()->{
                String name = "???";
                if("Thread-0".equals(Thread.currentThread().getName())) {
                    name = "hhy";
                } else {
                    name = "女朋友";
                }
                System.out.println(name + "到了gym");
                try {
                    // 两个线程都到了才能执行
                    cyclicBarrier.await();
                    System.out.println("时间是" + LocalDateTime.now() + " 跟" + name + "一起撸铁!");
                    Thread.sleep(5);
                    // 下一个动作
                    cyclicBarrier.await();
                    System.out.println("时间是" + LocalDateTime.now() + " " + name + "洗澡");
                    Thread.sleep(5);
                    // 下一个动作
                    cyclicBarrier.await();
                    System.out.println("时间是" + LocalDateTime.now() + " " + name + "睡觉");
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
