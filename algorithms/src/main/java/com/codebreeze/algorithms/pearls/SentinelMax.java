package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.function.ToIntFunction;

public class SentinelMax implements IntSupplier {
    private final int[] x;
    private final Algorithm algorithm;

    public SentinelMax(int[] x, Algorithm algorithm) {
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
    public int getAsInt() {
        return this.algorithm.applyAsInt(x);
    }

    enum Algorithm implements ToIntFunction<int[]> {
        /*
I := 1
while I <= N do
    Max := X[I]
    X[N+1] := Max
    I := I+1
    while X[I] < Max do
        I := I+1
         */
        Sentinel {
            @Override
            public int applyAsInt(int[] x) {
                int i = 0;
                int max = Integer.MIN_VALUE;
                while (i < x.length - 1) {
                    max = x[i];
                    x[x.length - 1] = max;
                    i = i + 1;
                    while (x[i] < max) {
                        i = i + 1;
                    }
                }
                return max;
            }
        },
        /*
I := 1
while I <= N do
    if(X[I] > Max)
        Max := X[I]
    I := I+1
                 */
        NoSentinel {
            @Override
            public int applyAsInt(int[] x) {
                int i = 0;
                int max = Integer.MIN_VALUE;
                while (i < x.length) {
                    if (x[i] > max) {
                        max = x[i];
                    }
                    i = i + 1;
                }
                return max;
            }
        }
    }
}
