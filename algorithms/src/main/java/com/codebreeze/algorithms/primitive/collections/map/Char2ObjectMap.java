package com.codebreeze.algorithms.primitive.collections.map;

import com.codebreeze.algorithms.primitive.collections.pair.CharObjectPair;

import java.util.stream.Stream;

public interface Char2ObjectMap<T> {
    void put(char key, T value);
    T get(char key);
    boolean containsKey(char key);
    void remove(char key);
    int size();
    Stream<CharObjectPair<T>> stream();
}
