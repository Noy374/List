package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация двусвязного списка.
 *
 * @param <T> тип элементов, хранящихся в связном списке
 */
public class LinkedList<T> implements List<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    /**
     * Внутренний класс, представляющий узел в связном списке.
     *
     * @param <T> тип элемента, хранящегося в узле
     */
    private static class LinkedListNode<T> {
        public LinkedListNode<T> next;
        public LinkedListNode<T> prev;
        public T info;

        public LinkedListNode(T info) {
            this.info = info;
        }
    }

    /**
     * Конструктор для создания пустого связного списка.
     */
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Добавляет элемент в конец связного списка.
     *
     * @param elem добавляемый элемент
     * @return true, если элемент успешно добавлен
     * @throws NullPointerException если переданный элемент равен null
     */
    @Override
    public boolean add(T elem) {
        Objects.requireNonNull(elem, "Элемент не может быть null");
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

    /**
     * Проверяет, содержит ли связный список заданный элемент.
     *
     * @param elem элемент для поиска
     * @return true, если элемент присутствует в списке, иначе false
     */
    @Override
    public boolean contains(T elem) {
        LinkedListNode<T> ptr = head;
        while (ptr != null) {
            if (Objects.equals(ptr.info, elem)) return true;
            ptr = ptr.next;
        }
        return false;
    }

    /**
     * Добавляет все элементы из коллекции в конец связного списка.
     *
     * @param c коллекция, содержащая добавляемые элементы
     * @return true, если все элементы успешно добавлены
     * @throws NullPointerException если переданный элемент равен null
     */
    @Override
    public boolean addAll(Collections<T> c) {
        Objects.requireNonNull(c, "Коллекция не может быть null");
        for (T elem : c) {
            add(elem);
        }
        return true;
    }

    /**
     * Удаляет указанный элемент из связного списка.
     *
     * @param elem удаляемый элемент
     * @return true, если элемент был найден и удален
     * @throws NullPointerException если переданный элемент равен null
     */
    @Override
    public boolean remove(T elem) {
        Objects.requireNonNull(elem, "Элемент не может быть null");
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

    /**
     * Удаляет указанный узел из связного списка.
     *
     * @param nodeToRemove удаляемый узел
     */
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

    /**
     * Очищает все элементы из связного списка.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Предоставляет итератор для связного списка.
     *
     * @return итератор по элементам связного списка
     */
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

    /**
     * Возвращает количество элементов в связном списке.
     *
     * @return размер связного списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли связный список.
     *
     * @return true, если связный список не содержит элементов, иначе false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Преобразует связный список в массив объектов.
     *
     * @return массив, содержащий все элементы связного списка
     */
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

    /**
     * Возвращает элемент по указанному индексу в связном списке.
     *
     * @param index индекс элемента для возврата
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Индекс за пределами диапазона");
        LinkedListNode<T> ptr = head;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        if (ptr == null) throw new NoSuchElementException("Элемент не найден по индексу: " + index);
        return ptr.info;
    }

    /**
     * Вставляет элемент в указанную позицию в связном списке.
     *
     * @param index индекс, в который вставляется элемент
     * @param elem  элемент для вставки
     * @throws NullPointerException      если элемент равен null
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @Override
    public void add(int index, T elem) {
        if (elem == null) throw new NullPointerException("Элемент не может быть null");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Индекс за пределами диапазона");
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

    /**
     * Удаляет элемент по указанному индексу из связного списка.
     *
     * @param index индекс удаляемого элемента
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @Override
    public T removeByIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Индекс за пределами диапазона");

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

    /**
     * Генерирует строковое представление связного списка.
     *
     * @return строка, содержащая все элементы связного списка
     */
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
