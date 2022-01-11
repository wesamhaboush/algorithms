package com.codebreeze.algorithms.primitive.collections.heap;

import com.codebreeze.algorithms.primitive.collections.Collection;

public interface DoubleHeap extends Collection {
    void insert(double d);
    double extractM();
}
