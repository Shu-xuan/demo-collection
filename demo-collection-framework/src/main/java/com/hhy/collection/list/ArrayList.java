package com.hhy.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class ArrayList<E> implements List<E> {
    private Object[] table = new Object[10];
    /**
     * 实际元素个数
     */
    private int size;

    @Override
    public void add(E element) {
        table[size++] = element;
        if (size == table.length) {
            resize();
        }
    }

    /**
     * 扩容为原数组的两倍大小
     */
    private void resize() {
        Object[] newTable = new Object[size * 2];
        System.arraycopy(table, 0, newTable, 0, size);
        this.table = newTable;
    }

    @Override
    public void add(E element, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        // 判断加上当前元素后是否需要扩容
        if (size + 1 >= table.length) {
            resize();
        }
        System.arraycopy(table, index, table, index + 1, size - index);
        table[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        E removeElement = (E) table[index];
        System.arraycopy(table, index + 1, table, index, size - index - 1);
        table[--size] = null;
        return removeElement;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, table[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        E oldElement = (E) table[index];
        table[index] = element;
        return oldElement;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        return (E) table[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    class ArrayListIterator implements Iterator<E> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            E element = (E) table[cursor++];
            return element;
        }
    }
}
