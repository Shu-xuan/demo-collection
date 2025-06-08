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
public class LinkedList<E> implements List<E>{
    private int size;
    private Node<E> head;
    private Node<E> tail;

    @Override
    public void add(E element) {
        Node<E> insert = new Node<>(tail, null, element);
        if (tail == null) {
            head = insert;
        } else {
            tail.next = insert;
        }
        tail = insert;
        size++;
    }

    @Override
    public void add(E element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        if (index == size) {
            add(element);
            return;
        }
        Node<E> next = findNode(index);
        Node<E> insert = new Node<>(next.prev, next, element);
        if (next.prev == null) {
            head = insert;
        } else {
            next.prev.next = insert;
        }
        next.prev = insert;
        size++;
    }

    private Node<E> findNode(int index) {
        Node<E> result;
        if (index < size / 2) {
            result = head;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = tail;
            for (int i = size - 1; i > index ; i--) {
                result = result.prev;
            }
        }
        return result;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        return removeNode(findNode(index)).value;
    }

    private Node<E> removeNode(Node<E> node) {
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }
        node.prev = null;
        node.next = null;
        size--;

        return node;
    }

    @Override
    public boolean remove(E element) {
        Node<E> dummy = head;
        while (dummy != null) {
            if (Objects.equals(dummy.value, element)) {
                removeNode(dummy);
                return true;
            }
            dummy = dummy.next;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        Node<E> node = findNode(index);
        E value = node.value;
        node.value = element;
        return value;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        return findNode(index).value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    class LinkedIterator implements Iterator<E>{
        Node<E> cursor = head;
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = cursor.value;
            cursor = cursor.next;
            return result;
        }
    }

    class Node<E> {
        Node<E> prev;
        Node<E> next;
        E value;

        public Node() {
        }

        public Node(E value) {
            this.value = value;
        }

        public Node(Node<E> prev, Node<E> next, E value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
}
