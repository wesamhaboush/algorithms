package com.codebreeze.algorithms.primitive.collections.iterable;

import com.codebreeze.algorithms.primitive.collections.iterator.ArrayIntIterator;
import com.codebreeze.algorithms.primitive.collections.iterator.IntIterator;

import java.util.Arrays;
import java.util.Objects;

public class ArrayIntIterable implements IntIterable {
    private final int[] data;

    public ArrayIntIterable(int... data) {
        this.data = Objects.requireNonNull(data, "cannot operate on null data arrays!");
    }

    @Override
    public IntIterator iterator() {
        return new ArrayIntIterator(Arrays.copyOf(data, data.length));
    }
}
