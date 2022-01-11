package com.codebreeze.algorithms.primitive.collections.heap;

import com.codebreeze.algorithms.primitive.collections.Collection;

import java.util.stream.Stream;

public interface Heap<T> extends Collection {
    void insert(T d);
    T extractM();
    Stream<T> stream();
}
