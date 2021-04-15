package com.codebreeze.algorithms;

public class Comparables {
    public static <T extends Comparable<T>> boolean lessThan(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public static <T extends Comparable<T>> boolean greaterThan(T first, T second) {
        return first.compareTo(second) > 0;
    }
}
