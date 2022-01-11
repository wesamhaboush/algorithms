package com.codebreeze.algorithms.pearls;

import java.util.Objects;
import java.util.function.Consumer;

import static org.apache.commons.lang3.ArrayUtils.swap;

/*
Note: most algorithms differ in how they do the partitioning to do quick sort.
 */
public class QuickSort3WayPartition implements Consumer<int[]> {

    @Override
    public void accept(int[] x) {
        Objects.requireNonNull(x, "cannot operate on null arrays");
        quicksort(x, 0, x.length - 1);
    }

    /* Partitions a[] in three parts
       a) x[l..i] contains all elements smaller than pivot
       b) x[i+1..j-1] contains all occurrences of pivot
       c) x[j..r] contains all elements greater than pivot */

    static Partition partition(int[] x, int l, int r) {
        int i = l - 1;
        int j = r;
        int p = l - 1;
        int q = r;
        int v = x[r];

        while (true) {

            // From left, find the first element greater than
            // or equal to v. This loop will definitely
            // terminate as v is last element
            do {
                i++;
            } while (x[i] < v);

            // From right, find the first element smaller than
            // or equal to v
            while (v < x[--j]) {
                if (j == l) {
                    break;
                }
            }

            // If i and j cross, then we are done
            if (i >= j) {
                break;
            }

            // Swap, so that smaller goes on left greater goes
            // on right
            swap(x, i, j);

            // Move all same left occurrence of pivot to
            // beginning of array and keep count using p
            if (x[i] == v) {
                p++;
                swap(x, p, i);
            }

            // Move all same right occurrence of pivot to end of
            // array and keep count using q
            if (x[j] == v) {
                q--;
                swap(x, q, j);
            }
        }

        // Move pivot element to its correct index
        swap(x, i, r);

        // Move all left same occurrences from beginning
        // to adjacent to x[i]
        j = i - 1;
        for (int k = l; k < p; k++, j--) {
            swap(x, k, j);
        }

        // Move all right same occurrences from end
        // to adjacent to x[i]
        i = i + 1;
        for (int k = r - 1; k > q; k--, i++) {
            swap(x, i, k);
        }
        return new Partition(i, j);
    }

    static void quicksort(int[] x, int l, int r) {
        if (r <= l) {
            return;
        }

        Partition partition = partition(x, l, r);

        // Recur
        quicksort(x, partition.left, r);
        quicksort(x, l, partition.right);
    }

    static class Partition {
        private final int left;
        private final int right;

        Partition(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
