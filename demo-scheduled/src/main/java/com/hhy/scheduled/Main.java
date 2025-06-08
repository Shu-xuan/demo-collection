package com.hhy.scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;古月HYuan</p>
 *
 * @Author 枢璇
 * @Date 2025/3/25 20:16
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");

        ScheduleService scheduleService = new ScheduleService();
        scheduleService.schedule(()->{
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + ": Ⅰ");
        }, 1000);
        Thread.sleep(1000);
        scheduleService.schedule(()->{
            System.out.println(LocalDateTime.now().format(dateTimeFormatter) + ": Ⅱ");
        }, 2000);

    }
}
