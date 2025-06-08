package com.hhy.thread.pool.callable;

import java.util.concurrent.Callable;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 14:05
 */
public class MyCallable implements Callable<Integer> {
    private int count;
    public MyCallable(int count) {
        this.count = count;
    }
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i = 1; i <= this.count; ++i) {
            sum += i;
        }
        return sum;
    }
}
