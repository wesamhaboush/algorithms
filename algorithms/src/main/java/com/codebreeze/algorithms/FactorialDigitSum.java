package com.codebreeze.algorithms;

import java.util.stream.IntStream;

public class FactorialDigitSum
{
    public static long calculate(final int n)
    {
        //first find number of digits required
        int numberOfDigits = numberOfDigitsOfFactorial(n);
        int[] digits = new int[numberOfDigits];
        digits[0] = 1;
        int currentNumber = 1;

        while (currentNumber < n)
        {
            currentNumber++;
            int carry = 0;
            for (int i = 0; i < digits.length; i++)
            {
                final int num = currentNumber * digits[i] + carry;
                digits[i] = num % 10;
                carry = num / 10;
            }
        }
        long sum = 0;
        for (int digit : digits)
        {
            sum += digit;
        }
        return sum;
    }

    private static int numberOfDigitsOfFactorial(final int n)
    {
        return (int) Math.ceil(IntStream
                .rangeClosed(1, n)
                .mapToDouble(Math::log10)
                .sum());
    }
}
