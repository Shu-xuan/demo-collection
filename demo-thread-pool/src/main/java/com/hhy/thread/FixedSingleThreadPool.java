package com.hhy.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class FixedSingleThreadPool {
    BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(1024);
    Thread cowHorse = new Thread(()->{
        while (true) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }, "唯一牛马");
    {
        cowHorse.start();
    }

    public void execute(Runnable command) {
        boolean offer = taskQueue.offer(command);

    }
}
