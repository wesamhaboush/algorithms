package com.codebreeze.algorithms.primitive.collections.map;

public interface Int2IntMap {
    void put(int key, int value);
    int get(int key);
    boolean containsKey(int key);
    void remove(int key);
}
