package com.hhy.aqs;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class Main {
    public static int count = 110000;
    public static void main(String[] args) throws InterruptedException {
        MyLock myLock = new MyLock();
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            threads.add(new Thread(() -> {
                myLock.lock();
                for (int j = 0; j < 1100; j++) {
                    count--;
                }
                myLock.unlock();
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(count);
    }
}
