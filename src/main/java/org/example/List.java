package org.example;

public interface List<T> extends Collections<T>{
    T get(int index);
    void add(int index, T elem);
    T remove(int index);
}