package com.hhy.thread.handler.impl;

import com.hhy.thread.MultiThreadPool;
import com.hhy.thread.handler.RejectHandler;

/**
 * <p>
 * 描述: 抛异常的拒绝策略
 * </p>
 *
 * @Author huhongyuan
 */
public class ThrowRejectHandler implements RejectHandler {
    @Override
    public void reject(Runnable rejectedTask, MultiThreadPool threadPool) {
        throw new RuntimeException("[ERROR] 阻塞队列满了");
    }
}
