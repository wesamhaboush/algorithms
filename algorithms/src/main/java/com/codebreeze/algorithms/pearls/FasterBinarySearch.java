package com.codebreeze.algorithms.pearls;

import java.util.concurrent.atomic.AtomicInteger;

public class FasterBinarySearch extends CountingBinarySearch {

    public FasterBinarySearch(int[] values) {
        super(values);
    }

    @Override
    public IntPair apply(int value) {
        AtomicInteger count = new AtomicInteger(0);
        int position = regularBinarySearch(values, value, count);
        // first is location, second is count of comparisons done
        return new IntPair(position, count.get());
    }

    @Override
    protected int getMid(int[] values, int value, int min, int max) {
        // see https://en.wikipedia.org/wiki/Interpolation_search
        //pos = low + ((key - arr[low]) * (high - low) / (arr[high] - arr[low]));
        return min + ((value - values[min]) * (max - min) / (values[max] - values[min]));
    }
}

