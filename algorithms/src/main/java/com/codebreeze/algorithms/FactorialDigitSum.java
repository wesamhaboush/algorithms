package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!
 */
public class FactorialDigitSum
{
    public static long calculate(final int n)
    {
        final int numberOfDigits = numberOfDigitsOfFactorial(n);
        final int[] digits = new int[numberOfDigits];
        digits[0] = 1;
        int targetDigitNumber = 1;

        while (targetDigitNumber < n)
        {
            targetDigitNumber++;
            doMultiplicationForTargetDigit(digits, targetDigitNumber);
        }
        return Arrays.stream(digits)
                     .sum();
    }

    private static void doMultiplicationForTargetDigit(final int[] digits, final int currentNumber)
    {
        int carry = 0;
        for (int i = 0; i < digits.length; i++)
        {
            final int num = currentNumber * digits[i] + carry;
            digits[i] = num % 10;
            carry = num / 10;
        }
    }

    private static int numberOfDigitsOfFactorial(final int n)
    {
        return (int) Math.ceil(IntStream
                .rangeClosed(1, n)
                .mapToDouble(Math::log10)
                .sum());
    }
}
