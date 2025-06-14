package com.hhy.thread;

import com.hhy.thread.handler.RejectHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class MultiThreadPool {
    /**
     * 不死线程数
     */
    private final int corePoolSize;

    /**
     * 最大线程数
     */
    private final int maxPoolSize;

    /**
     * 任务队列
     */
    public BlockingQueue<Runnable> taskQueue;

    /**
     * 临时线程存活时间
     */
    private int keepAliveTime;

    /**
     * 存活时间单位，默认为秒
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    /**
     * 拒绝策略
     */
    private RejectHandler rejectHandler;

    public MultiThreadPool() {
        this.corePoolSize = Runtime.getRuntime().availableProcessors();
        this.maxPoolSize = corePoolSize * 2;
        this.taskQueue = new ArrayBlockingQueue<>(1024);
        this.keepAliveTime = 1;
    }

    public MultiThreadPool(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = corePoolSize * 2;
        this.taskQueue = new ArrayBlockingQueue<>(1024);
        this.keepAliveTime = 1;
    }

    public MultiThreadPool(int corePoolSize, int maxPoolSize) {
        if (corePoolSize >= maxPoolSize) {
            throw new IllegalArgumentException("最大线程数不能小于核心线程数!!!");
        }
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.taskQueue = new ArrayBlockingQueue<>(1024);
        this.keepAliveTime = 1;
    }

    public MultiThreadPool(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> taskQueue) {
        this(corePoolSize, maxPoolSize);
        this.taskQueue = taskQueue;
    }

    public MultiThreadPool(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> taskQueue, int keepAliveTime, TimeUnit timeUnit) {
        this(corePoolSize, maxPoolSize, taskQueue);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
    }

    public MultiThreadPool(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> taskQueue, int keepAliveTime, TimeUnit timeUnit, RejectHandler rejectHandler) {
        this(corePoolSize, maxPoolSize, taskQueue);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.rejectHandler = rejectHandler;
    }

    // 核心线程
    List<Thread> coreCowHorseList = new ArrayList<>();
    // 额外线程
    List<Thread> extraCowHorseList = new ArrayList<>();


    /**
     * 有线程安全问题，非原子操作
     */
    public void execute(Runnable command) {
        // 核心线程未达上限
        if (corePoolSize > coreCowHorseList.size()) {
            Thread newCoreThread = new CoreThread("牛马线程-" + (coreCowHorseList.size() + 1));
            coreCowHorseList.add(newCoreThread);
            newCoreThread.start();
        }
        if (taskQueue.offer(command)) {
            return;
        }

        // 任务队列满了
        if ((maxPoolSize - corePoolSize) > extraCowHorseList.size()) {
            Thread newExtraThread = new ExtraThread("打工线程-" + (extraCowHorseList.size() + 1));
            extraCowHorseList.add(newExtraThread);
            newExtraThread.start();
        }

        // 任务队列仍然是满的
        if (!taskQueue.offer(command)) {
            // 拒绝策略
            this.rejectHandler.reject(command, this);
        }

    }

    class CoreThread extends Thread {
        public CoreThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class ExtraThread extends Thread {
        public ExtraThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.poll(keepAliveTime, timeUnit);
                    if (task == null) {
                        break;
                    }
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + "已结束!");
        }
    }

}
