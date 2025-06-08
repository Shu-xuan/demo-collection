package com.hhy.collection.map;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapTest {

    private static final int THREAD_COUNT = 10;
    private static final int ELEMENT_COUNT = 100000;

    @Test
    public void testcon() throws InterruptedException {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        // 提交多个线程进行并发插入
        for (int i = 0; i < THREAD_COUNT; i++) {
            int threadId = i;
            executorService.submit(() -> {
                for (int j = 0; j < ELEMENT_COUNT; j++) {
                    int key = threadId * ELEMENT_COUNT + j;
                    map.put(key, "Value-" + key);
                }
            });
        }

        // 关闭线程池并等待所有任务完成
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        // 验证插入的元素数量
        System.out.println("ConcurrentHashMap size: " + map.size());

        // 验证读取的元素
        for (int i = 0; i < THREAD_COUNT * ELEMENT_COUNT; i++) {
            String value = map.get(i);
            if (value == null || !value.equals("Value-" + i)) {
                System.out.println("Error: Key " + i + " not found or incorrect value: " + value);
            }
        }

        System.out.println("All elements are correctly inserted and retrieved.");
    }
}
