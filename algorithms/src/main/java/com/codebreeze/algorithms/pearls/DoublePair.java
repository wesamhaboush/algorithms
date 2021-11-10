package com.codebreeze.algorithms.pearls;

record DoublePair(double left, double right) {
    public static DoublePair of(double left, double right) {
        return new DoublePair(left, right);
    }
}
