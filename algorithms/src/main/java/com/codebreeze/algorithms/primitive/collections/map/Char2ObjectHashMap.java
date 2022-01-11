package com.codebreeze.algorithms.primitive.collections.map;

import com.codebreeze.algorithms.primitive.collections.pair.CharObjectPair;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Char2ObjectHashMap<T> implements Char2ObjectMap<T> {
    private final int bucketCount;
    private final List<List<CharObjectPair<T>>> buckets;
    private int size;

    public Char2ObjectHashMap(int bucketCount) {
        this.bucketCount = bucketCount;
        this.buckets = new LinkedList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    @Override
    public void put(char key, T value) {
        List<CharObjectPair<T>> bucket = buckets.get(bucketNumber(key));
        if (!exists(bucket, key)) {
            bucket.add(new CharObjectPair<T>(key, value));
            size++;
        } else {
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).first() == key) {
                    bucket.set(i, new CharObjectPair<>(key, value));
                }
            }
        }
    }

    private boolean exists(List<CharObjectPair<T>> bucket, char key) {
        for (CharObjectPair<T> entry : bucket) {
            if (entry.first() == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(char key) {
        for (CharObjectPair<T> entry : bucket(key)) {
            if (entry.first() == key) {
                return entry.second();
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(char key) {
        return exists(bucket(key), key);
    }

    @Override
    public void remove(char key) {
        List<CharObjectPair<T>> bucket = buckets.get(bucketNumber(key));
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).first() == key) {
                bucket.remove(i);
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
    public Stream<CharObjectPair<T>> stream() {
        Stream<CharObjectPair<T>> stream = Stream.of();
        for(List<CharObjectPair<T>> bucket : buckets) {
            stream = Stream.concat(stream, bucket.stream());
        }
        return stream;
    }

    private List<CharObjectPair<T>> bucket(char key) {
        return buckets.get(bucketNumber(key));
    }

    private int bucketNumber(char key) {
        return key % bucketCount;
    }
}
