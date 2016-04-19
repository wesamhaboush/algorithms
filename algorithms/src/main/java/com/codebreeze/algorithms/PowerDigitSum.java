package com.codebreeze.algorithms;
/*
Power digit sum
Problem 16
2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 21000?

 */
public class PowerDigitSum
{
    public static long calculate(final int base, final int exp) {
        int numberOfDigits = (int) Math.ceil(exp * Math.log10(base));
        int[] digits = new int[numberOfDigits];
        digits[0] = base;
        int currentExp = 1;

        while (currentExp < exp)
        {
            currentExp++;
            int carry = 0;
            for (int i = 0; i < digits.length; i++)
            {
                final int num = base * digits[i] + carry;
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
}
