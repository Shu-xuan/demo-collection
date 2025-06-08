package com.hhy.collection.map;

import com.hhy.collection.map.MyArrayHashMap;
import com.hhy.collection.map.MyArrayMap;
import com.hhy.collection.map.MyHashMap;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * <p>
 * 描述: 测试
 * </p>
 *
 * @Author huhongyuan
 * @Date 2025/4/7 11:54
 */
public class TestHash {
    @Test
    public void testArrayMap() {
        MyArrayMap<String, String> table = new MyArrayMap<>();
        int trueSize = 100000;

        for (int i = 0; i < trueSize; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(table.size(), trueSize);
        table.remove("8");
        assertNull(table.get("8"));
        assertEquals(table.size(), trueSize - 1);

    }

    @Test
    public void testArrayHashMap() {
        MyArrayHashMap<String, String> table = new MyArrayHashMap<>(10);
        int trueSize = 100000;

        for (int i = 0; i < trueSize; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(table.size(), trueSize);

        for (int i = 0; i < trueSize; i++) {
            assertEquals(String.valueOf(i), table.get(String.valueOf(i)));
        }
    }
    @Test
    public void testHashMap() {
        MyHashMap<String, String> table = new MyHashMap<>();
        int trueSize = 1000000;

        for (int i = 0; i < trueSize; i++) {
            table.put(String.valueOf(i), String.valueOf(i));
        }
        assertEquals(table.size(), trueSize);

        for (int i = 0; i < trueSize; i++) {
            assertEquals(String.valueOf(i), table.get(String.valueOf(i)));
        }
    }

    @Test
    public void testConcurrent() {
        new ConcurrentHashMap<>();
    }
}
