package com.codebreeze.algorithms.primitive.collections.map;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Int2IntHashMap implements Int2IntMap {
    private final int bucketCount;
    private final IntIntEntry[][] buckets;

    public Int2IntHashMap(int bucketCount) {
        this.bucketCount = bucketCount;
        this.buckets = new IntIntEntry[bucketCount][];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new IntIntEntry[0];
        }
    }

    @Override
    public void put(int key, int value) {
        IntIntEntry[] currentItems = bucket(key);
        if(!exists(currentItems, key)) {
            IntIntEntry[] newItems = Arrays.copyOf(currentItems, currentItems.length + 1);
            newItems[newItems.length - 1] = new IntIntEntry(key, value);
            buckets[bucketNumber(key)] = newItems;
        }
    }

    private boolean exists(IntIntEntry[] bucket, int key) {
        for(IntIntEntry entry : bucket) {
            if(entry.key == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int get(int key) {
        for (IntIntEntry currentItem : bucket(key)) {
            if (currentItem.key() == key) {
                return currentItem.value();
            }
        }
        throw new NoSuchElementException("cannot find value for key: " + key);
    }

    @Override
    public boolean containsKey(int key) {
        for (IntIntEntry currentItem : bucket(key)) {
            if (currentItem.key() == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(int key) {
        IntIntEntry[] bucket = bucket(key);
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i].key() == key) {
                bucket[i] = null;
                buckets[bucketNumber(key)] = Arrays.stream(bucket).filter(Objects::nonNull).toArray(IntIntEntry[]::new);
            }
        }
    }

    private IntIntEntry[] bucket(int key) {
        return buckets[bucketNumber(key)];
    }

    private int bucketNumber(int key) {
        return Math.abs(key) % bucketCount;
    }

    private record IntIntEntry(int key, int value) {
    }
}
