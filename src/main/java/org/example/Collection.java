package org.example;


import java.util.Iterator;

public interface Collection<T> extends Iterable<T> {

    boolean add(T elem);
    boolean contains(T elem);
    boolean addAll(Collection<T> c);
    boolean remove(T elem);
    void clear();
    Iterator<T> iterator();
    int size();
    boolean isEmpty();
    Object[] toArray();
}