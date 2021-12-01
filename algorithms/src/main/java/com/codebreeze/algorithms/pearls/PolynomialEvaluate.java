package com.codebreeze.algorithms.pearls;

import java.util.function.IntUnaryOperator;

public class PolynomialEvaluate implements IntUnaryOperator {
    private final int[] a;
    private final Algorithm algorithm;

    public PolynomialEvaluate(int[] a, Algorithm algorithm) {
        if (a == null) {
            throw new NullPointerException("cannot operate on null coefficient array");
        }
        if (algorithm == null) {
            throw new NullPointerException("cannot operate without a specified algorithm!");
        }
        this.a = a;
        this.algorithm = algorithm;
    }

    @Override
    public int applyAsInt(int x) {
        return algorithm.apply(a, x);
    }

    enum Algorithm {
        /*
Y := A[N]
for I := N-1 downto 0 do
    Y := X*Y + A[I]
         */
        N {
            @Override
            int apply(int[] a, int x) {
                int y = a[a.length - 1];
                for (int i = a.length - 2; i >= 0; i--) {
                    y = x * y + a[i];
                }
                return y;
            }
        },
        /*
Y := A[0]
XToTheI := 1
for I := 1 to N do
    XToTheI := X*XToTheI
    Y := Y + A[I]*XToTheI
         */
        NN {
            @Override
            int apply(int[] a, int x) {
                int y = a[0];
                int xToTheI = 1;
                for (int i = 1; i < a.length; i++) {
                    xToTheI = x * xToTheI;
                    y = y + a[i] * xToTheI;
                }
                return y;
            }
        };

        abstract int apply(int[] a, int x);
    }
}
