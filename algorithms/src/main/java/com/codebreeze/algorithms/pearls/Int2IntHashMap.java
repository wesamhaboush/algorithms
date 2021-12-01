package com.codebreeze.algorithms.pearls;

import java.util.Arrays;

public class Int2IntHashMap {
    private final int bucketCount;
    private final int[][] buckets;
//    private final int[] sums;

    Int2IntHashMap(int bucketCount) {
        this.bucketCount = bucketCount;
        this.buckets = new int[bucketCount][];
//        this.sums = new int[this.bucketCount + 1];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new int[0];
        }
    }

    void add(int value) {
        int bucketNumber = value % bucketCount;
        int[] currentItems = buckets[bucketNumber];
        int[] newItems = Arrays.copyOf(currentItems, currentItems.length + 1);
        newItems[newItems.length - 1] = value;
        buckets[bucketNumber] = newItems;
//        for (int i = bucketNumber; i < bucketCount; i++) {
//            this.sums[i+1] += 1;
//        }
    }

    int find(int value) {
        int bucketNumber = Math.abs(value) % bucketCount;
        int[] currentItems = buckets[bucketNumber];
        for (int i = 0; i < currentItems.length; i++) {
            if (currentItems[i] == value) {
//                return this.sums[i] + i;
                return i;
            }
        }
        return -1;
    }
}
