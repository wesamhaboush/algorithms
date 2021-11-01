package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
Given a set of `N` real numbers, and a real number `T`, and an integer `K`,
how quickly can you determine where there exists a `K`-element subset of the set that sums to at most `T`?
 */
public class SumInSet {

    public boolean test(int[] elements, int subsetSize, int targetSum) {
        if (subsetSize > elements.length) {
            throw new IllegalArgumentException(
                String.format("cannot have a subset of size larger than the set size. set size: %d, subset size: %d", elements.length, subsetSize)
            );
        }
        Arrays.sort(elements);
        return IntStream.range(0, elements.length)
            .takeWhile(i -> i < subsetSize)
            .map(i -> elements[i])
            .sum() <= targetSum;
    }
}
