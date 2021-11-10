package com.codebreeze.algorithms;

import java.util.NoSuchElementException;
import java.util.function.ToIntBiFunction;

public class FirstOccurrenceBinarySearch<T extends Comparable<T>> implements ToIntBiFunction<T[], T> {
    @Override
    public int applyAsInt(T[] ts, T t) {
        int min = 0;
        int max = ts.length - 1;
        while (max >= min) {
            int mid = (min + max) / 2;
            if (t.equals(ts[mid])) {
                /*
                replace this whole thing with `

                return mid`

                to go back to vanilla binary search
                 */
                // if prior element is the same, then we are not at first occurrence,
                // we need to assume we have not found it.
                if(mid > 0 && t.equals(ts[mid - 1])) {
                    max = mid - 1;
                } else {
                    return mid;
                }
            } else if (t.compareTo(ts[mid]) > 0) { // t is greater than middle
                min = mid + 1;
            } else if (t.compareTo(ts[mid]) < 0) { // t is less than middle
                max = mid - 1;
            }
        }
        throw new NoSuchElementException("cannot find element " + t);
    }
}
