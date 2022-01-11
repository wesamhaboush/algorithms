package com.codebreeze.algorithms.primitive.collections;

public interface Collection {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
