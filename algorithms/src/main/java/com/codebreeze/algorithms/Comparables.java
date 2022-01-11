package com.codebreeze.algorithms;

import java.util.Comparator;

public class Comparables {
    public static <T extends Comparable<T>> boolean lessThan(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public static <T extends Comparable<T>> boolean lessThanOrEqualTo(T first, T second) {
        return first.compareTo(second) <= 0;
    }

    public static <T extends Comparable<T>> boolean lt(T first, T second) {
        return lessThan(first, second);
    }

    public static <T extends Comparable<T>> boolean greaterThan(T first, T second) {
        return first.compareTo(second) > 0;
    }

    public static <T extends Comparable<T>> boolean greaterThanOrEqualTo(T first, T second) {
        return first.compareTo(second) >= 0;
    }

    public static <T extends Comparable<T>> boolean gt(T first, T second) {
        return greaterThan(first, second);
    }

    public static <T extends Comparable<T>> boolean eq(T first, T second) {
        return first.compareTo(second) == 0;
    }

    public static <T> boolean gt(Comparator<T> c, T t1, T t2) {
        return greaterThan(c, t1, t2);
    }

    public static <T> boolean greaterThan(Comparator<T> c, T t1, T t2) {
        return c.compare(t1, t2) > 0;
    }

    public static <T> boolean gte(Comparator<T> c, T t1, T t2) {
        return greaterThanOrEqualTo(c, t1, t2);
    }

    public static <T> boolean greaterThanOrEqualTo(Comparator<T> c, T t1, T t2) {
        return c.compare(t1, t2) >= 0;
    }

    public static <T> boolean lt(Comparator<T> c, T t1, T t2) {
        return lessThan(c, t1, t2);
    }

    public static <T> boolean lessThan(Comparator<T> c, T t1, T t2) {
        return c.compare(t1, t2) < 0;
    }

    public static <T> boolean lte(Comparator<T> c, T t1, T t2) {
        return lessThanOrEqualTo(c, t1, t2);
    }

    public static <T> boolean lessThanOrEqualTo(Comparator<T> c, T t1, T t2) {
        return c.compare(t1, t2) < 0;
    }

    public static <T> boolean eq(Comparator<T> c, T t1, T t2) {
        return c.compare(t1, t2) == 0;
    }
}
