package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class RadixSortInts implements Consumer<int[]> {

    @Override
    public void accept(int[] x) {
        Objects.requireNonNull(x, "cannot operate on null arrays");
        radixSort(x);
    }

    private static int maxOf(int[] x) {
        int max = x[0];
        for (int i = 1; i < x.length; i++) {
            if (x[i] > max) {
                max = x[i];
            }
        }
        return max;
    }

    // Counting sort of x[] according to the digit represented by exp.
    private static void countSort(int[] x, int exp) {
        int[] output = new int[x.length]; // output array
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (int i : x) {
            count[(i / exp) % 10]++;
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = x.length - 1; i >= 0; i--) {
            int remainder = (x[i] / exp) % 10;
            output[count[remainder] - 1] = x[i];
            count[remainder]--;
        }

        // Copy the output array to x[], so that x[] now
        // contains sorted numbers according to current digit
        System.arraycopy(output, 0, x, 0, x.length);
    }

    private static void radixSort(int[] x) {
        if(x.length == 0) {
            return;
        }
        int max = maxOf(x);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(x, exp);
        }
    }
}
