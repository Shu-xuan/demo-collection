package com.hhy.blocking.linked;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 描述: 简易链表版阻塞队列
 * </p>
 *
 * @Author huhongyuan
 * @Date 2025/4/5 11:56
 */
public class MyLinkedBlockingQueue<E> {
    private final int capacity;

    private Lock w = new ReentrantLock();
    private Lock r = new ReentrantLock();

    static class Node<E> {
        private E item;

        private Node next;

        Node(E x) {
            item = x;
        }
    }

    private Node<E> head;
    private Node<E> tail;


    /**
     * 构造函数
     * @param capacity
     */
    public MyLinkedBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("容量必须大于0");
        this.capacity = capacity;
        head = tail = new Node<>(null);
    }

    /**
     * 读取
     */
    public E dequeue() {
        try {
            r.lock();

        } finally {
            r.unlock();
        }
        return null;
    }

    /**
     * 写入
     */
    public void enqueue(E x) {
        try {
            w.lock();

        } finally {
            w.unlock();
        }
    }
}
