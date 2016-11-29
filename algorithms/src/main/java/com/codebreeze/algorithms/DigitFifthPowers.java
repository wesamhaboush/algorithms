package com.codebreeze.algorithms;

import java.util.stream.LongStream;

import static java.lang.Math.floor;
import static java.lang.Math.log10;

/*
Digit fifth powers
Problem 30
Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

1634 = 14 + 64 + 34 + 44
8208 = 84 + 24 + 04 + 84
9474 = 94 + 44 + 74 + 44
As 1 = 14 is not a sum it is not included.

The sum of these numbers is 1634 + 8208 + 9474 = 19316.

Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
public class DigitFifthPowers
{
    /*
    Note: the return has an extra one on the sum because it considers 1 as a good candidate,
    but actually, it is NOT a sum, so we should not consider it. So in the test, i wrote
    a test case for that.
     */
    public static long calculate(final int n)
    {
        final long min = 0;
        final long max = Power.apply(9, n) * n;
        return LongStream.rangeClosed(min, max)
                  .filter(i -> i == toNthPowerOfDigits(i, n))
                  .peek(System.out::println)
                  .sum();
    }

    private static long toNthPowerOfDigits(final long i, final int n)
    {
        final int digitCount = (int) floor(log10(i)) + 1;
        long sum = 0;
        long number = i;
        for(int j = 1; j <= digitCount; j++)
        {
            final long remainder = number % 10;
            number = number / 10;
            sum += Power.apply(remainder, n);
        }
        return sum;
    }
}
