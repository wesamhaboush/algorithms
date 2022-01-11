package com.codebreeze.algorithms.primitive.collections.map;

import com.codebreeze.algorithms.primitive.collections.pair.CharIntPair;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

public class Char2IntHashMap implements Char2IntMap {
    private final int bucketCount;
    private final CharIntPair[][] buckets;
    private int size;

    public Char2IntHashMap(int bucketCount) {
        this.bucketCount = bucketCount;
        this.buckets = new CharIntPair[bucketCount][];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new CharIntPair[0];
        }
        this.size = 0;
    }

    @Override
    public void put(char key, int value) {
        CharIntPair[] bucket = bucket(key);
        if (!exists(bucket, key)) {
            CharIntPair[] newItems = Arrays.copyOf(bucket, bucket.length + 1);
            newItems[newItems.length - 1] = new CharIntPair(key, value);
            buckets[bucketNumber(key)] = newItems;
            size++;
        } else {
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i].first() == key) {
                    bucket[i] = new CharIntPair(key, value);
                }
            }
        }
    }

    private boolean exists(CharIntPair[] bucket, int key) {
        for (CharIntPair entry : bucket) {
            if (entry.first() == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int get(char key) {
        for (CharIntPair entry : bucket(key)) {
            if (entry.first() == key) {
                return entry.second();
            }
        }
        throw new NoSuchElementException("cannot find value for key: " + key);
    }

    @Override
    public boolean containsKey(char key) {
        return exists(bucket(key), key);
    }

    @Override
    public void remove(char key) {
        CharIntPair[] bucket = bucket(key);
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i].first() == key) {
                bucket[i] = null;
                buckets[bucketNumber(key)] = Arrays.stream(bucket).filter(Objects::nonNull).toArray(CharIntPair[]::new);
                size--;
                return;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Stream<CharIntPair> stream() {
        Stream<CharIntPair> stream = Stream.of();
        for(CharIntPair[] entryArray : buckets) {
            stream = Stream.concat(stream, Arrays.stream(entryArray));
        }
        return stream;
    }

    private int bucketNumber(char key) {
        return Objects.hash(key) % bucketCount;
    }

    private CharIntPair[] bucket(char key) {
        return buckets[bucketNumber(key)];
    }
}
