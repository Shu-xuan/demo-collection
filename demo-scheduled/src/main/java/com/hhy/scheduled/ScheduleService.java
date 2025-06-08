package com.hhy.scheduled;

import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;古月HYuan</p>
 *
 * @Author 枢璇
 * @Date 2025/3/25 20:17
 */
public class ScheduleService {
    ExecutorService executor = Executors.newFixedThreadPool(6);

    Trigger trigger = new Trigger();

    public void schedule(Runnable task, long delay) {
        Job job = new Job();
        job.setTask(task);
        job.setDelay(delay);
        job.setStartTime(System.currentTimeMillis() + delay);
        trigger.queue.offer(job);
        trigger.wakeup();
    }

    class Trigger {
        private PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>(16, Comparator.comparingLong(Job::getStartTime));

        Thread thread = new Thread(()->{
            while (true) {
                // while防止虚假唤醒
                while (queue.isEmpty()) {
                    LockSupport.park();
                }
                // peek 不会将获取的对象队列中删除
                Job latestJob = queue.peek();
                if (latestJob.getStartTime() < System.currentTimeMillis()) {
                    // 拿到最短的任务，直接执行
                    latestJob = queue.poll();
                    executor.execute(latestJob.getTask());
                    // 计算下一次执行时间
                    Job nextJob = new Job();
                    nextJob.setTask(latestJob.getTask());
                    nextJob.setDelay(latestJob.getDelay());
                    nextJob.setStartTime(System.currentTimeMillis() + latestJob.getDelay());
                    // 加入队列
                    queue.offer(nextJob);
                } else {
                    // 最近的任务都需要等待时
                    LockSupport.parkUntil(latestJob.getStartTime());
                }
            }
        });
        {
            System.out.println("触发器启动了...");
            thread.start();
        }
        void wakeup() {
            LockSupport.unpark(thread);
        }

    }
}
