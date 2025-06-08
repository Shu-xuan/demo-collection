package com.hhy.aqs;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 * 描述: aqs锁
 * </p>
 *
 * @Author huhongyuan
 */
public class MyLock {
    AtomicBoolean locked = new AtomicBoolean(false);
    AtomicReference<Thread> owner = new AtomicReference<>();
    AtomicReference<Node> head = new AtomicReference<>(new Node());
    AtomicReference<Node> tail = new AtomicReference<>(head.get());

    public void lock() {
        Thread currentThread = Thread.currentThread();
        // CAS获取锁，成功就返回
        if (locked.compareAndSet(false, true)) {
            owner.set(currentThread);
            System.out.println(currentThread.getName() + "持锁成功!");
            return;
        }
        // 失败则加入阻塞队列，直到成功
        Node waitNode = new Node(currentThread);
        while (true) {
            Node curTail = tail.get();
            if (tail.compareAndSet(curTail, waitNode)) {
                waitNode.prev = curTail;
                curTail.next = waitNode;
                break;
            }
        }
        while (true) {
            System.out.println(currentThread.getName() + "阻塞等待!");
            if (waitNode.prev == head.get() && locked.compareAndSet(false, true)) {
                System.out.println(currentThread.getName() + "持锁成功!");
                owner.set(currentThread);
                waitNode.prev.next = null;
                waitNode.prev = null;
                head.set(waitNode);
                return;
            }
            LockSupport.park();
        }

    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        if (currentThread != owner.get()) {
            System.out.println(currentThread.getName() + "为非持锁线程，无法释放锁!");
            return;
        }
        locked.set(false);
        Node toAware = head.get().next;
        if (toAware != null) {
            LockSupport.unpark(toAware.thread);
            System.out.println(currentThread.getName() + "释放锁成功");
        }

    }

    static class Node {
        Node prev;
        Node next;
        Thread thread;

        public Node() {
        }

        public Node(Thread thread) {
            this.thread = thread;
        }
    }
}
