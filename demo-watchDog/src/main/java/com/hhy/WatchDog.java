package com.hhy;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 描述: 看门狗
 * </p>
 *
 * @Author huhongyuan
 */
public class WatchDog {
    private static volatile boolean feeded = true;
    /**
     * 最大续期次数
     */
    private int maxRenewTimes;
    /**
     * 当前续期次数
     */
    private int currentRenewTimes;
    /**
     * 重试次数
     */
    private int retryTimes = 3;
    /**
     * 延迟时间
     */
    private int initDelay = 0;
    /**
     * 周期
     */
    private int timeout = 10;
    /**
     * 时间单位
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public WatchDog() {
    }

    public WatchDog(int renewTimes, int retryTimes) {
        this.retryTimes = retryTimes;
        this.maxRenewTimes = renewTimes;
        this.currentRenewTimes = renewTimes;
    }

    public WatchDog(int renewTimes, int retryTimes, int timeout, TimeUnit timeUnit) {
        this.retryTimes = retryTimes;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.maxRenewTimes = renewTimes;
        this.currentRenewTimes = renewTimes;
    }

    public WatchDog(int renewTimes, int retryTimes, int initDelay, int timeout, TimeUnit timeUnit) {
        this.retryTimes = retryTimes;
        this.initDelay = initDelay;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.maxRenewTimes = renewTimes;
        this.currentRenewTimes = renewTimes;
    }

    private static ExecutorService executor = Executors.newFixedThreadPool(2);
    private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void feed() {
        feeded = true;
    }

    public void startWatch(Runnable bizTask) {
        Future<?> initialBizTh = executor.submit(() -> {
            try {
                bizTask.run();
            } finally {
                feeded = false;
            }
        });
        AtomicReference<Future<?>> bizThRef = new AtomicReference<>(initialBizTh);

        Runnable watchDogTask = () -> {
            if (bizThRef.get().isDone()) {
                System.out.println("业务已完成");
                stop();
                return;
            }
            if (!feeded) {
                System.out.println("未收到心跳，判定业务异常");
                if (--retryTimes >= 0) {
                    System.out.println("尝试重试，剩余重试次数: " + retryTimes);
                    System.out.println("当前时间: " + LocalDateTime.now());
                    bizThRef.set(executor.submit(bizTask));
                    currentRenewTimes = maxRenewTimes;
                } else {
                    System.out.println("重试次数耗尽，终止监控");
                    stop();
                }
            } else {
                feeded = false;
                System.out.println("当前时间: " + LocalDateTime.now());
                System.out.println("心跳正常，已续期，剩余续期次数: " + --currentRenewTimes);
                if (currentRenewTimes <= 0) {
                    System.out.println("续期次数耗尽");
                    stop();
                }
            }
        };
        scheduler.scheduleAtFixedRate(watchDogTask, initDelay, timeout, timeUnit);
    }

    public void stop() {
        scheduler.shutdown();
        executor.shutdown();
        System.out.println("看门狗已停止");
    }

}


