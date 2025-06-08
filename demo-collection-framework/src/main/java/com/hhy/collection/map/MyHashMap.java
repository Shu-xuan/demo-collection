package com.hhy.collection.map;

/**
 * <p>
 * 描述: 数组映射表
 * </p>
 *
 * @Author huhongyuan
 * @Date 2025/4/7 11:39
 */
public class MyHashMap<K, V> {
    private static int capacity = 10;

    private static final double PAYLOAD_FACTOR = 0.75f;

    private int size = 0;

    Node<K, V>[] table;

    public MyHashMap(int size) {
        this.capacity = size;
        table = new Node[capacity];
    }

    public MyHashMap() {
        this(capacity);
    }

    /**
     * 插入方法
     */
    public V put(K k, V v) {
        int kIndex = indexOf(k);
        Node<K, V> kvNode = table[kIndex];
        if (kvNode == null) {
            table[kIndex] = new Node<>(k, v);
            sizeIncrement();
            return null;
        }
        while (indexOf(kvNode.key) == kIndex) {
            if (kvNode.key.equals(k)) {
                // 相同的key直接覆盖赋值
                V oldValue = kvNode.value;
                kvNode.value = v;
                return oldValue;
            }
            if (kvNode.next == null) {
                kvNode.next = new Node<>(k, v);
                sizeIncrement();
                return null;
            }
            kvNode = kvNode.next;
        }

        return null;
    }

    /**
     * 获取方法
     */
    public V get(K k) {
        // 找到下标
        int kIndex = indexOf(k);
        // 找到链表
        Node<K, V> head = table[kIndex];
        while (head != null) {
            if (head.key.equals(k)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    /**
     * 删除方法
     */
    public V remove(K k) {
        int kIndex = indexOf(k);
        Node<K, V> head = table[kIndex];
        if (head == null) {
            return null;
        }
        if (head.key.equals(k)) {
            table[kIndex] = head.next;
            --size;
            return head.value;
        }
        // 保存头
        Node<K, V> prev = head;
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(k)) {
                prev.next = current.next;
                --size;
                return current.value;
            }
            prev = prev.next;
            current = current.next;
        }
        return null;
    }

    /**
     * 随机存取
     * 拿到下标
     */
    private int indexOf(K key) {
        return key.hashCode() & capacity - 1;
    }

    private void doResize() {
        if (size < capacity * PAYLOAD_FACTOR) {
            return;
        }
        capacity *= 2;
        System.out.println("扩容后的长度为: " + capacity);
        // 二倍大小的新数组
        Node<K, V>[] newMap = new Node[capacity];
        // 旧的移过去
        for (Node<K, V> oldNode : table) {
            if (oldNode == null) {
                continue;
            }
            Node<K,V> current = oldNode;
            while (current != null) {
                int newIndex = indexOf(current.key);
                if (newMap[newIndex] == null) {
                    newMap[newIndex] = current;
                    // 断掉原来的连接
                    Node<K, V> next = current.next;
                    current.next = null;
                    current = next;
                } else {
                    // 当前索引位置为链表,头插法
                    Node<K, V> next = current.next;
                    current.next = newMap[newIndex];
                    newMap[newIndex] = current;
                    current = next;
                }
            }
        }
        table = newMap;

    }
    private void sizeIncrement() {
        ++size;
        doResize();
    }

    public int size() {
        return size;
    }

    class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Node<K, V> next;
    }

}
