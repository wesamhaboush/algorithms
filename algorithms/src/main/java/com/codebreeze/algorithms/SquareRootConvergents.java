package com.codebreeze.algorithms;

import java.util.function.IntUnaryOperator;

import static com.codebreeze.algorithms.BigIntegerFraction.bif;
import static com.codebreeze.algorithms.BigIntegers.digitCount;
import static java.math.BigInteger.ONE;

/*
Problem 57

It is possible to show that the square root of two can be expressed as an infinite continued intFraction.

√ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

By expanding this for the first four iterations, we get:

1 + 1/2 = 3/2 = 1.5
1 + 1/(2 + 1/2) = 7/5 = 1.4
1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...

The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.

In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?


Solution:

We will break the denominator from 2 + 1/2 to 1 + 1 + 1/2, this will allow us to create a kind of recursive function (or easy for loop or stream)

1 => n_0
1 + 1/2 => 1 + 1/(1 + n_0) => n_1
1 + 1/(2 + 1/2) => 1 + 1 / (1 + n_1) => n_2
etc

 */
public class SquareRootConvergents implements IntUnaryOperator {


    @Override
    public int applyAsInt(int numberOfExpansions) {
        if (numberOfExpansions < 0) {
            throw new IllegalArgumentException("cannot accept negative numbers");
        }

        int count = 0;
        BigIntegerFraction fraction = bif(ONE);
        for (int i = 0; i < numberOfExpansions; i++) {
            fraction = bif(ONE).add( // 1 + 1 / (1 + f(n - 1))
                    bif(ONE).divide(
                            bif(ONE).add(
                                    fraction
                            )
                    )
            );
            System.out.println(i + ":" + fraction);
            if (
                    digitCount(fraction.getNumerator()) >
                            digitCount(fraction.getDenominator())
            ) {
                count++;
            }
        }
        return count;
    }
}
