package com.codebreeze.algorithms.primitive.collections.map;

public interface Char2ShortMap<T> {
    void put(char key, T value);
    T get(char key);
    boolean containsKey(char key);
    void remove(char key);
}
