package com.codebreeze.algorithms.pearls2;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class MaxMin implements Function<int[], MaxMin.MinMaxInt> {
    private final Algorithm algorithm;

    public MaxMin(Algorithm algorithm) {
        requireNonNull(algorithm, "cannot operate with null algorithm");
        this.algorithm = algorithm;
    }

    @Override
    public MinMaxInt apply(int[] elements) {
        requireNonNull(elements, "cannot operate with null elements");
        if(elements.length == 0) {
            throw new IllegalArgumentException("cannot operate on zero length arrays");
        }
        return algorithm.apply(elements);
    }

    enum Algorithm implements Function<int[], MaxMin.MinMaxInt> {
        /*
        Max := Min := X[1]
        for I := 2 to 1000 do
            if X[I] > Max then Max := X[I]
            if X[I] < Min then Min := X[I]
         */
        A {
            @Override
            public MinMaxInt apply(int[] x) {
                int min = x[0];
                int max = x[0];
                int comparisonCount = 0;
                for(int i = 1; i < x.length; i++) {
                    if(x[i] > max) max = x[i];
                    if(x[i] < min) min = x[i];
                    comparisonCount += 2;
                }
                return new MinMaxInt(min, max, comparisonCount);
            }
        },
        /*
        Max := Min := X[1]
        for I := 2 to 1000 do
            if      X[I] > Max then Max := X[I]
            else if X[I] < Min then Min := X[I]
         */
        B {
            @Override
            public MinMaxInt apply(int[] x) {
                int min = x[0];
                int max = x[0];
                int comparisonCount = 0;
                for(int i = 1; i < x.length; i++) {
                    // this is just to figure out comparison count
                    if(x[i] > max) comparisonCount++;
                    else comparisonCount += 2; // two comparisons happen if first one fails!

                    if(x[i] > max) max = x[i];
                    else if(x[i] < min) min = x[i];
                }
                return new MinMaxInt(min, max, comparisonCount);
            }
        }
    }

    public record MinMaxInt(int min, int max, int comparisonCount) {
    }
}
