package com.codebreeze.algorithms.primitive.collections;

import java.util.List;

public class Lists {
    public static <T> T valueOrNull(List<T> heap, int index) {
        if (index < 0 || index >= heap.size()) {
            return null;
        } else {
            return heap.get(index);
        }
    }
}
