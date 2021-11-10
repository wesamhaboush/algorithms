package com.codebreeze.algorithms.pearls;

import java.util.function.BiFunction;
import java.util.function.ToIntFunction;

public class Verification {
    static class AddVectors implements BiFunction<int[], int[], int[]> {

        @Override
        public int[] apply(int[] b, int[] c) {
            if (b.length != c.length) {
                throw new IllegalArgumentException("cannot operate on vectors with different sizes");
            }
            int[] a = new int[b.length];
            int i = -1;
            while (++i < b.length) {
                a[i] = b[i] + c[i];
            }
            return a;
        }
    }

    static class Max implements ToIntFunction<int[]> {

        @Override
        public int applyAsInt(int[] vector) {
            if (vector.length == 0) {
                throw new IllegalArgumentException("cannot operate on empty arrays");
            }
            int i = 2;
            int max = vector[0];
            while (i < vector.length) {
                if (vector[i] > max) {
                    max = vector[i];
                }
                i++;
            }
            return max;
        }
    }

    static class LinearSearch {

        public int apply(int[] vector, int target) {
            int i = 0;
            while (i < vector.length && vector[i] != target) {
                i++;
            }
            if (i == vector.length) {
                return -1;
            } else {
                return i;
            }
        }
    }

    static class Exponential {

        public int apply(int x, int n) {
            if (n < 0) {
                throw new IllegalArgumentException("cannot operate on negative exponentials");
            }
            if (n == 0) {
                return 1;
            } else if (n % 2 == 0) {
                int halfX = apply(x, n / 2);
                return halfX * halfX;
            } else {
                return x * apply(x, n - 1);
            }
        }
    }
}
