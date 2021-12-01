package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

import static org.apache.commons.lang3.ArrayUtils.swap;

public final class InsertionSort implements Consumer<int[]> {
    private final Algorithm algorithm;
    static boolean debug = false;

    public InsertionSort(Algorithm algorithm) {
        if (algorithm == null) {
            throw new NullPointerException(
                "cannot work without you specifying algorithm from values: " + Arrays.toString(Algorithm.values())
            );
        }
        this.algorithm = algorithm;
    }

    @Override
    public void accept(int[] n) {
        if (n == null) {
            throw new NullPointerException("cannot sort null arrays");
        }
        algorithm.accept(n);
    }

    /*
    for I := 2 to N do
        // Invariant: X[1..I-1] is sorted
        J := I
        while J > 1 and X[J-1] > X[J] do
            Swap(X[J], X[J-1])
            J := J-1

     The idea here is to move each value backward until it goes to its rightful place. Everytime
     we increase I, everything else up to that I is in good conditions, i.e. sorted!
     Note: it is almost like a bubble sort within the range 0 to i-1. So you expand i, then you bubble
     that value to its rightful place
     */
    private static void slow(int[] x) {
        if (debug) {
            System.out.println("slow: ");
            System.out.println("x: " + Arrays.toString(x));
        }
        for (int i = 1; i < x.length; i++) {
            int j = i;
            while (j > 0 && x[j - 1] > x[j]) {
                if (debug) {
                    System.out.printf(
                        "x: %s, swapping (x[j=%d]=%d, x[j - 1=%d]=%d)%n",
                        Arrays.toString(x), j, x[j], j - 1, x[j - 1]
                    );
                }
                swap(x, j, j - 1);
                if (debug) {
                    System.out.printf(
                        "x: %s, swapped (x[j=%d]=%d, x[j - 1=%d]=%d)%n",
                        Arrays.toString(x), j, x[j], j - 1, x[j - 1]
                    );
                }
                j = j - 1;
            }
        }
    }

    /*
    for I := 2 to N do
        // Invariant: X[1..I-1] is sorted
        J := I
        T := X[J]
        while J > 1 and X[J-1] > T do
            X[J] := X[J-1]
            J := J-1
        X[J] := T

    This one moves in the opposite direction from the slow, where basically, you take the element
    you are going to tackle aside, then, you keep moving the elements before forward until you find
    the slot where the element fits, and then you insert it. for example:
    [1,2,7,8,9,10,3]
    if the current element to tackle is 3, then you take that aside, move 9, 8, and 7 forward, then
    you insert three in the old 7 spot!
    [1,2,7,8,9,10,10] // move 10 forward
    [1,2,7,8,9,9,10] // move 9 forward
    [1,2,7,8,8,9] // move 8 forward
    [1,2,7,8,8,9,10] // move 8 forward
    [1,2,7,7,8,9,10] // move 7 forward
    // now insert the 3!
    [1,2,3,7,8,9,10] //now sorted after finding the rightful slot for 3

    Note: the fast is faster because there are 1/3 number of assignments involved on average. Swapping requires
    thee assignments, but shifting requires one only!
     */
    private static void fast(int[] x) {
        if (debug) {
            System.out.println("fast: ");
            System.out.println("x: " + Arrays.toString(x));
        }
        for (int i = 1; i < x.length; i++) {
            int j = i;
            int t = x[j];
            if (debug) {
                System.out.printf(
                    "x: %s, tackling x[j=%d]=%d%n",
                    Arrays.toString(x), j, x[j]
                );
            }
            while (j > 0 && x[j - 1] > t) {
                if (debug) {
                    System.out.printf(
                        "x: %s, copying x[j-1=%d]=%d to x[j=%d]=%d%n",
                        Arrays.toString(x), j-1, x[j - 1], j, x[j]
                    );
                }
                x[j] = x[j - 1];
                if (debug) {
                    System.out.printf(
                        "x: %s, copied x[j-1=%d]=%d to x[j=%d]=%d%n",
                        Arrays.toString(x), j-1, x[j - 1], j, x[j]
                    );
                }
                j = j - 1;
            }
            if (debug) {
                System.out.printf(
                    "x: %s, inserting t=%d to x[j=%d]=%d%n",
                    Arrays.toString(x), t, j, x[j]
                );
            }
            x[j] = t;
            if (debug) {
                System.out.printf(
                    "x: %s, inserted t=%d to x[j=%d]=%d%n",
                    Arrays.toString(x), t, j, x[j]
                );
            }
        }
    }

    public Algorithm algorithm() {
        return algorithm;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (InsertionSort) obj;
        return Objects.equals(this.algorithm, that.algorithm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(algorithm);
    }

    @Override
    public String toString() {
        return "InsertionSort[" +
            "algorithm=" + algorithm + ']';
    }


    enum Algorithm implements Consumer<int[]> {
        Slow {
            @Override
            public void accept(int[] n) {
                slow(n);
            }
        },
        Fast {
            @Override
            public void accept(int[] n) {
                fast(n);
            }
        }
    }
}
