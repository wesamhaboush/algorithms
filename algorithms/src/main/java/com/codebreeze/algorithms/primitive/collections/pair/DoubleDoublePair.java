package com.codebreeze.algorithms.primitive.collections.pair;

public record DoubleDoublePair(double first, double second) {
    public static DoubleDoublePair of(double first, double second) {
        return new DoubleDoublePair(first, second);
    }
}
