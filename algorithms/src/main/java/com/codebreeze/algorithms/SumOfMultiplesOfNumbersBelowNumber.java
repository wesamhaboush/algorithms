package com.codebreeze.algorithms;

import java.util.Arrays;

public class SumOfMultiplesOfNumbersBelowNumber
{
    public static int calculate(int n, int[] nums)
    {
        return sumOfMultiplesOf(n, nums);
    }

    public static int sumOfMultiplesOf(int n, int[] numbers)
    {
        if(n < 1)
        {
            return 0;
        }
        if(isAnyMultipleOfNumber(n - 1, numbers))
        {
            return (n - 1) + sumOfMultiplesOf( --n, numbers );
        }
        else
        {
            return sumOfMultiplesOf( --n, numbers );
        }
    }

    private static boolean isAnyMultipleOfNumber(int n, int[] numbers) {
        return Arrays
                .stream(numbers)
                .anyMatch( number -> isMultipleOf(n, number) );
    }

    private static boolean isMultipleOf(int n, int number) {
        return n % number == 0;
    }
}
