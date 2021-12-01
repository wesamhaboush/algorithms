package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

import static java.lang.Math.abs;
import static java.util.Comparator.comparingInt;

public class RangeNearestSumToZero implements IntUnaryOperator {
    private final int[] x;

    public RangeNearestSumToZero(int[] x) {
        if (x == null) {
            throw new NullPointerException("cannot operate on null arrays");
        }
        this.x = Arrays.copyOf(x, x.length);
    }


    // return i
    @Override
    public int applyAsInt(int m) {
        if (m < 0 || m > x.length) {
            throw new IllegalArgumentException("size of range cannot be larger than available vector or less than zero:" + m);
        }
        Cumulative[] cumArray = new Cumulative[x.length];
        for (int i = 0; i < x.length; i++) {
            // so for array 0, 1, 2, 3, 4, 5, 6
            // when m = 3, we need cumulative array that looks like
            // 0, 0, 3, 6 - 0, 10 - 1, 15 - 3, 21 - 6
            // 0, 0, 3, 6, 9, 12, 15
            int sumUpToHere =
                (i == 0 ? 0 : cumArray[i - 1].value) // 0 used for the first element
                    + x[i]
                    - ((i - m) < 0 ? 0 : x[i - m]); // if we are still not more than m elements, subtract 0
            cumArray[i] = Cumulative.of(sumUpToHere, abs(sumUpToHere), i);
        }
        // now we need to remove all elements that are less than m size, which are < m in their end index
        return Arrays.stream(cumArray)
            .filter(c -> c.u >= m - 1)
            .min(comparingInt(a -> a.absValue))
            .orElseThrow().value;
    }

    private record Cumulative(int value, int absValue, int u) {
        static Cumulative of(int value, int absValue, int u) {
            return new Cumulative(value, absValue, u);
        }
    }
}
