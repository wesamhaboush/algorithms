package com.codebreeze.algorithms;

import java.util.NoSuchElementException;
import java.util.function.ToIntBiFunction;

public class FirstOccurrenceBinarySearchRecursive<T extends Comparable<T>> implements ToIntBiFunction<T[], T> {
    @Override
    public int applyAsInt(T[] ts, T t) {
        return binarySearch(ts, t, 0, ts.length - 1);
    }

    private int binarySearch(T[] ts, T t, int min, int max) {
        if(min > max) {
            throw new NoSuchElementException("cannot find element " + t);
        }
        int mid = (min + max) / 2;
        if (t.equals(ts[mid])) {
                /*
                replace this whole thing with `

                return mid`

                to go back to vanilla binary search
                 */
            // if prior element is the same, then we are not at first occurrence,
            // we need to assume we have not found it.
            if (mid > 0 && t.equals(ts[mid - 1])) {
                return binarySearch(ts, t, min, mid - 1);
            } else {
                return mid;
            }
        } else if (t.compareTo(ts[mid]) > 0) { // t is greater than middle
            return binarySearch(ts, t, mid + 1, max);
        } else if (t.compareTo(ts[mid]) < 0) { // t is less than middle
            return binarySearch(ts, t, min, mid - 1);
        } else {
            throw new IllegalStateException("should have NEVER come here!! what!!");
        }
    }
}
