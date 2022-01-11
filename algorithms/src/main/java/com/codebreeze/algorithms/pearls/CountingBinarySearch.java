package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.pair.IntIntPair;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;

public class CountingBinarySearch implements IntFunction<IntIntPair> {
    protected final int[] values;

    public CountingBinarySearch(int[] values) {
        if (values == null) {
            throw new NullPointerException("cannot operate with null values array");
        }
        this.values = Arrays.copyOf(values, values.length);
    }

    @Override
    public IntIntPair apply(int value) {
        AtomicInteger count = new AtomicInteger(0);
        int position = regularBinarySearch(values, value, count);
        // first is location, second is count of comparisons done
        return new IntIntPair(position, count.get());
    }

    protected int regularBinarySearch(int[] values, int value, AtomicInteger count) {
        int min = 0;
        int max = values.length - 1;
        while (min <= max) {
            int mid = getMid(values, value, min, max);
            count.set(count.get() + 1);
            if (values[mid] == value) {
                return mid;
            } else if (values[mid] > value) {
                max = mid - 1;
                //return regularBinarySearch(values, value, min, mid - 1, count);
            } else { // else if (values[mid] < value)
                min = mid + 1;
                //return regularBinarySearch((values, value, mid + 1, max, count);
            }
        }
        return -1;
    }

    protected int getMid(int[] values, int value, int min, int max) {
        return (min + max) / 2;
    }
}
