package linked;

import java.util.HashMap;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class LRUCache {
    private final HashMap<Integer, Node> cache = new HashMap<>();

    private int capacity;

    private int curCapacity;

    private static Node head = new Node();
    private static Node tail = new Node();
    static  {
        head.next = tail;
        tail.prev = head;
    }

    static class Node {
        Integer k;
        Integer v;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        curCapacity = 0;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 移动到队头
        mov2head(node);
        return node.v;
    }

    private void mov2head(Node node) {
        Node next = head.next;
        head.next = node;
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        // 已存在则更新值
        if (node != null) {
            node.v = value;
            mov2head(node);
            return;
        }
        while (curCapacity >= capacity) {
            // 移除队尾
            Node toRm = tail.prev;
            cache.remove(toRm.k);
            removeTail();
        }
        // 新节点则头插
        Node newNode = new Node(key, value);
        add2Head(newNode);
        cache.put(key, newNode);
    }

    private void removeTail() {
        Node last = tail.prev;
        if (last != head) { // 避免删除头哨兵
            last.prev.next = tail;
            tail.prev = last.prev;
            decrement();
        }
    }

    private void increment() {
        this.curCapacity ++;
    }
    private void decrement() {
        this.curCapacity --;
    }

    private void add2Head(Node newNode) {
        mov2head(newNode);
        increment();
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 0);
        lRUCache.put(2, 2);
        System.out.println(lRUCache.get(1));
        lRUCache.put(3, 3);
        System.out.println(lRUCache.get(2));
        lRUCache.put(4, 4);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
    }
}
