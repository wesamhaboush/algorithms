package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.function.IntFunction;

public class CountOnesInBinary implements IntFunction<int[]> {
    private final Algorithm algorithm;

    public CountOnesInBinary(Algorithm algorithm) {
        if (algorithm == null) {
            throw new NullPointerException(
                "cannot work without you specifying algorithm from values: " + Arrays.toString(Algorithm.values())
            );
        }
        this.algorithm = algorithm;
    }

    @Override
    public int[] apply(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("cannot create arrays of negative size!!");
        }
        return algorithm.apply(n);
    }

    private static int[] impl2(int n) {
        int[] c = new int[n];
        int p = 1;
        int pp = p + p;
        for (int j = 1; j < n; j++) {
            if (j == pp) {
                p = pp;
                pp = p + p;
            }
            c[j] = c[j - p] + 1;
        }
        return c;
    }

    private static int[] impl1(int n) {
        int[] c = new int[n];
        int p = 1;
        while (p < n) {
            for (int j = 0; j < p; j++) {
                c[p + j] = c[j] + 1;
            }
            p = p + p;
        }
        return c;
    }

    enum Algorithm implements IntFunction<int[]> {
        Bentley {
            @Override
            public int[] apply(int n) {
                return impl1(n);
            }
        },
        Boer {
            @Override
            public int[] apply(int n) {
                return impl2(n);
            }
        }
    }
}
