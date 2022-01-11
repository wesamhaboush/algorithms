package com.codebreeze.algorithms.primitive.collections.iterator;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayIntIterator implements IntIterator {
    private final int[] data;
    private int currIntIndex;

    public ArrayIntIterator(int... data) {
        this.data = Arrays.copyOf(data, data.length);
        this.currIntIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currIntIndex < data.length;
    }

    @Override
    public int next() {
        if (currIntIndex >= data.length) {
            throw new NoSuchElementException("Attempt to iterate past iterator's last element.");
        }
        return data[currIntIndex++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("cannot remove item from input arrays to this iterator. Arrays are immutable in java in terms of size!");
    }
}
