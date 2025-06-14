package com.hhy.thread.handler;

import com.hhy.thread.MultiThreadPool;

/**
 * <p>
 * 描述: 拒绝处理器
 * </p>
 *
 * @Author huhongyuan
 */
public interface RejectHandler {
    void reject(Runnable rejectedTask, MultiThreadPool threadPool);
}
