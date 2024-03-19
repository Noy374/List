package org.example;

public interface List<T> extends Collection<T> {
    T get(int index);
    void add(int index, T elem);
    T removeByIndex(int index);
}