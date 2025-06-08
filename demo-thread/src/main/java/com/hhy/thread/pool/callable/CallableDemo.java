package com.hhy.thread.pool.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 14:07
 */
public class CallableDemo {
    public static void main(String[] args) {
        // 创建线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = pool.submit(new MyCallable(100));
        Future<Integer> future2 = pool.submit(new MyCallable(200));
        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }
}
