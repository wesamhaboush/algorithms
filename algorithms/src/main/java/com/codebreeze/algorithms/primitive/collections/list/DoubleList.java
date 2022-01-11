package com.codebreeze.algorithms.primitive.collections.list;

import java.util.stream.DoubleStream;

public interface DoubleList {
    void add(double value);
    void remove(double value);
    void removeAt(int index);
    double get(int index);
    void set(int index, double value);
    boolean contains(double value);
    default boolean isEmpty() {
        return size() == 0;
    }
    int size();
    DoubleStream stream();
    default void swap(int index1, int index2) {
        double v1 = this.get(index1);
        double v2 = this.get(index2);
        this.set(index1, v2);
        this.set(index2, v1);
    }
}
