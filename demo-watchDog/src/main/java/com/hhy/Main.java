package com.hhy;

import java.time.LocalDateTime;

/**
 * <p>
 * 描述:
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        WatchDog dog = new WatchDog(3, 2); // 3次续期，2次重试

        dog.startWatch(() -> {
            int feedTimes = 5;
//            long startTime = System.currentTimeMillis();
            try {
                while (--feedTimes >= 0) {
                    dog.feed(); // 喂狗
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
//            System.out.println("业务完成，总耗时: " + (System.currentTimeMillis() - startTime));
        });

    }
}
