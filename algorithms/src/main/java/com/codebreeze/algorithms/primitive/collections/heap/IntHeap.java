package com.codebreeze.algorithms.primitive.collections.heap;

import com.codebreeze.algorithms.primitive.collections.Collection;

public interface IntHeap extends Collection {
    void insert(int i);
    int extractM();
}
