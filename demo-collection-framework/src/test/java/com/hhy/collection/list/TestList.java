package com.hhy.collection.list;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */

public class TestList {
    @Test
    public void testList() {
        List<String> arr = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            arr.add(String.valueOf(i));
        }
        assertEquals(arr.size(), 30);
        arr.remove(15);
        arr.remove("18");
        assertEquals(arr.size(), 28);
        assertEquals(arr.get(15), "16");
        assertEquals(arr.get(22), "24");

        arr.forEach(System.out::println);
    }
}
