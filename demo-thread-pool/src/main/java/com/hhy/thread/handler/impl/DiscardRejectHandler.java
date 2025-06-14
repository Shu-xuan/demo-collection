package com.hhy.thread.handler.impl;

import com.hhy.thread.MultiThreadPool;
import com.hhy.thread.handler.RejectHandler;

/**
 * <p>
 * 描述: 丢弃策略
 * </p>
 *
 * @Author huhongyuan
 */
public class DiscardRejectHandler implements RejectHandler {

    @Override
    public void reject(Runnable rejectedTask, MultiThreadPool threadPool) {
        threadPool.taskQueue.poll();
        threadPool.execute(rejectedTask);
    }
}
