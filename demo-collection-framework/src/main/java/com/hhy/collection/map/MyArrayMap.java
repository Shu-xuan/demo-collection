package com.hhy.collection.map;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述: 数组映射表
 * </p>
 *
 * @Author huhongyuan
 * @Date 2025/4/7 11:39
 */
public class MyArrayMap<K, V> {
    List<Node<K, V>> table = new ArrayList();

    /**
     * 插入方法
     */
    public V put(K k, V v) {
        for (Node<K, V> kvNode : table) {
            if (kvNode.key.equals(k)) {
                V oldV = kvNode.value;
                kvNode.value = v;
                // 返回旧值
                return oldV;
            }
        }
        Node<K, V> newNode = new Node<>(k, v);
        table.add(newNode);
        return null;
    }

    /**
     * 获取方法
     */
    public V get(K k) {
        for (Node<K, V> kvNode : table) {
            if (kvNode.key.equals(k)) {
                return kvNode.value;
            }
        }
        return null;
    }

    /**
     * 删除方法
     */
    public V remove(K k) {
        for (int i = 0; i < size(); i++) {
            if (table.get(i).key.equals(k)) {
                Node<K, V> remove = this.table.remove(i);
                return remove.value;
            }
        }
        return null;
    }

    public int size() {
        return this.table.size();
    }

    class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
