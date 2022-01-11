package com.codebreeze.algorithms.primitive.collections.map;

import com.codebreeze.algorithms.primitive.collections.pair.CharIntPair;

import java.util.stream.Stream;

public interface Char2IntMap {
    void put(char key, int value);
    int get(char key);
    boolean containsKey(char key);
    void remove(char key);
    int size();
    Stream<CharIntPair> stream();
}
