package com.codebreeze.algorithms.pearls;

import java.util.Objects;
import java.util.function.Consumer;

public class RadixSortStrings implements Consumer<String[]> {
    private static final int R = 256;

    @Override
    public void accept(String[] x) {
        Objects.requireNonNull(x, "cannot operate on null arrays");
        radixSort(x);
    }


    private static void radixSort(String[] a) {
        String[] aux = new String[a.length];
        radixSort(a, 0, a.length - 1, 0, aux);
    }

    private static int charAt(String s, int d) {
        if (d == s.length())
            return -1;
        return s.charAt(d);
    }

    private static void radixSort(String[] a, int lo, int hi, int d, String[] aux) {

        if (hi <= lo) {
            return;
        }

        int[] count = new int[R + 2];

        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c + 2]++;
        }

        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c + 1]++] = a[i];
        }

        System.arraycopy(aux, 0, a, lo, hi + 1 - lo);

        for (int r = 0; r < R; r++) {
            radixSort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
        }
    }
}
