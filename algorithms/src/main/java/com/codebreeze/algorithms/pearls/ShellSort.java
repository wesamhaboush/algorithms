package com.codebreeze.algorithms.pearls;

import java.util.Objects;
import java.util.function.Consumer;

public class ShellSort implements Consumer<int[]> {
    @Override
    public void accept(int[] x) {
        Objects.requireNonNull(x, "cannot operate on null arrays");
        sort(x);
    }

    private static void sort(int[] x) {

        // Start with a big gap, then reduce the gap
        for (int gap = x.length / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements x[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < x.length; i++) {
                // add x[i] to the elements that have been gap
                // sorted save x[i] in temp and make a hole at
                // position i
                int temp = x[i];

                // shift earlier gap-sorted elements up until
                // the correct location for x[i] is found
                int j;
                for (j = i; j >= gap && x[j - gap] > temp; j -= gap) {
                    x[j] = x[j - gap];
                }

                // put temp (the original x[i]) in its correct
                // location
                x[j] = temp;
            }
        }
    }
}
