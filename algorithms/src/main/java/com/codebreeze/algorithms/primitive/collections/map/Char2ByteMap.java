package com.codebreeze.algorithms.primitive.collections.map;

public interface Char2ByteMap {
    void put(char key, int value);
    int get(char key);
    boolean containsKey(char key);
    void remove(char key);
}
