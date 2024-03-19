package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    private static class LinkedListNode<T> {
        public LinkedListNode<T> next;
        public LinkedListNode<T> prev;
        public T info;

        public LinkedListNode(T info) {
            this.info = info;
        }
    }

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(T elem) {
        Objects.requireNonNull(elem, "Element cannot be null");
        LinkedListNode<T> newNode = new LinkedListNode<>(elem);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean contains(T elem) {
        LinkedListNode<T> ptr = head;
        while (ptr != null) {
            if (Objects.equals(ptr.info, elem)) return true;
            ptr = ptr.next;
        }
        return false;
    }

    @Override
    public boolean addAll(Collections<T> c) {
        Objects.requireNonNull(c, "Collection cannot be null");
        for (T elem : c) {
            add(elem);
        }
        return true;
    }

    private void removeNode(LinkedListNode<T> nodeToRemove) {
        if (nodeToRemove == null) return;
        if (nodeToRemove == head) {
            head = head.next;
            if (head == null) tail = null;
            else head.prev = null;
        } else {
            LinkedListNode<T> prevNode = nodeToRemove.prev;
            prevNode.next = nodeToRemove.next;
            if (nodeToRemove == tail) tail = prevNode;
            else nodeToRemove.next.prev = prevNode;
        }
        size--;
    }

    @Override
    public boolean remove(T elem) {
        Objects.requireNonNull(elem, "Element cannot be null");
        LinkedListNode<T> ptr = head;
        while (ptr != null) {
            if (Objects.equals(ptr.info, elem)) {
                removeNode(ptr);
                return true;
            }
            ptr = ptr.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            LinkedListNode<T> ptr = head;

            @Override
            public boolean hasNext() {
                return ptr != null;
            }

            @Override
            public T next() {
                T info = ptr.info;
                ptr = ptr.next;
                return info;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        LinkedListNode<T> ptr = head;
        int i = 0;
        while (ptr != null) {
            arr[i++] = ptr.info;
            ptr = ptr.next;
        }
        return arr;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");
        LinkedListNode<T> ptr = head;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        if (ptr == null) throw new NoSuchElementException("No element found at index: " + index);
        return ptr.info;
    }


    @Override
    public void add(int index, T elem) {
        if (elem == null) throw new NullPointerException("Element cannot be null");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds");
        LinkedListNode<T> newNode = new LinkedListNode<>(elem);
        if (index == 0) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            LinkedListNode<T> ptr = head;
            for (int i = 0; i < index - 1; i++) {
                ptr = ptr.next;
            }
            newNode.next = ptr.next;
            newNode.prev = ptr;
            if (ptr.next != null) ptr.next.prev = newNode;
            ptr.next = newNode;
        }
        size++;
    }


    @Override
    public T removeByIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");

        LinkedListNode<T> ptr = head;
        if (index == 0) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (index == size - 1) {
            ptr = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            for (int i = 0; i < index; i++) {
                ptr = ptr.next;
            }
            removeNode(ptr);
        }
        size--;
        return ptr.info;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        LinkedListNode<T> ptr = head;
        while (ptr != null) {
            str.append(ptr.info != null ? ptr.info.toString() : "null").append(" ");
            ptr = ptr.next;
        }
        return str.toString();
    }
}
