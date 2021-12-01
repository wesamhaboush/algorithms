package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class SentinelLinearSearch implements IntPredicate {
    private final int[] x;
    private final Algorithm algorithm;

    public SentinelLinearSearch(int[] x, Algorithm algorithm) {
        if (x == null) {
            throw new NullPointerException("cannot operate on null X array");
        }
        if (algorithm == null) {
            throw new NullPointerException("cannot operate without a specified algorithm!");
        }
        this.algorithm = algorithm;
        this.x = Arrays.copyOf(x, x.length + 1);
    }

    @Override
    public boolean test(int t) {
        x[x.length - 1] = t;
        return this.algorithm.apply(x, t);
    }

    enum Algorithm {
        /*
        X[N+1] := T
        I := 1;
        while X[I] != T do I := I+1
         */
        Sentinel {
            @Override
            boolean apply(int[] x, int t) {
                int i = 0;
                while (x[i] != t)
                    i = i + 1;
                return i != x.length - 1;
            }
        },
        /*
        I := 1
        while I <= N and X[I] != T do I := I + 1
                 */
        NoSentinel {
            @Override
            boolean apply(int[] x, int t) {
                int i = 0;
                while (i < x.length && x[i] != t) {
                    i = i + 1;
                }
                return i != x.length - 1;
            }
        };

        abstract boolean apply(int[] x, int t);
    }
}
