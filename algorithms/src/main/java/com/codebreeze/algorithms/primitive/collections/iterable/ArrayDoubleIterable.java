package com.codebreeze.algorithms.primitive.collections.iterable;

import com.codebreeze.algorithms.primitive.collections.iterator.ArrayDoubleIterator;
import com.codebreeze.algorithms.primitive.collections.iterator.DoubleIterator;

import java.util.Arrays;
import java.util.Objects;

public class ArrayDoubleIterable implements DoubleIterable {
    private final double[] data;

    public ArrayDoubleIterable(double... data) {
        this.data = Objects.requireNonNull(data, "cannot operate on null data arrays!");
    }

    @Override
    public DoubleIterator iterator() {
        return new ArrayDoubleIterator(Arrays.copyOf(data, data.length));
    }
}
