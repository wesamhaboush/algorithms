package com.codebreeze.algorithms.primitive.collections.iterator;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayDoubleIterator implements DoubleIterator {
    private final double[] data;
    private int currIndex;

    public ArrayDoubleIterator(double... data) {
        this.data = Arrays.copyOf(data, data.length);
        this.currIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currIndex < data.length;
    }

    @Override
    public double next() {
        if (currIndex >= data.length) {
            throw new NoSuchElementException("Attempt to iterate past iterator's last element.");
        }
        return data[currIndex++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("cannot remove item from input arrays to this iterator. Arrays are immutable in java in terms of size!");
    }
}
