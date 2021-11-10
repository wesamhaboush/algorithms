package com.codebreeze.algorithms.pearls;

import java.util.Arrays;

public class AddVToX {
    private final int[] cumulative;

    public AddVToX(int n) {
        if(n < 0) {
            throw new IllegalArgumentException("cannot operate on negative array sizes!! " + n);
        }
        this.cumulative = new int[n];
    }

    void add(int v, int l, int u) {
        if (l < 0 || u >= cumulative.length) {
            throw new IllegalArgumentException(
                "cannot operate on lowers less than zero or uppers greater than " + cumulative.length
            );
        }
        if (l > u) {
            throw new IllegalArgumentException("lower cannot be greater than upper bounds! " + l + ", " + u);
        }

//        cumulative[u] = cumulative[u] + v;
//        if (l > 0) {
//            cumulative[l - 1] = cumulative[l - 1] - v;
//        }

        cumulative[l] = cumulative[l] + v;
        if (u != cumulative.length - 1) {
            cumulative[u + 1] = cumulative[u + 1] - v;
        }
    }

    int[] getX() {
//        int[] x = Arrays.copyOf(cumulative, cumulative.length);
//        for (int i = x.length - 2; i >= 0; i--) {
//            x[i] = x[i + 1] - x[i];
//        }
//        return x;
        // convert array into prefix sum array
        int[] x = Arrays.copyOf(cumulative, cumulative.length);
        for (int i = 1; i < x.length; i++) {
            x[i] = x[i] + x[i - 1];
        }
        return x;
    }
}
