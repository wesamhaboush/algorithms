package com.codebreeze.algorithms.primitive.collections.list;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public interface IntList {
    void add(int value);
    void remove(int value);
    void removeAt(int index);
    int get(int index);
    void set(int index, int value);
    int size();
    boolean contains(int value);
    default boolean isEmpty() {
        return size() == 0;
    }
    IntStream stream();
    default void swap(int index1, int index2) {
        int v1 = this.get(index1);
        int v2 = this.get(index2);
        this.set(index1, v2);
        this.set(index2, v1);
    }
    default int last() {
        if(isEmpty()) {
            throw new NoSuchElementException("cannot get last element from an empty list");
        }
        return get(size() - 1);
    }
    void clear();
}
