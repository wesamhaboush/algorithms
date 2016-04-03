package com.codebreeze.algorithms;

public class SumOfMultiplesOf3And5BelowNumber
{
    public static int calculate(int n)
    {
        return sumOfMultiplesOf(n, 5) + sumOfMultiplesOf(n, 3) - sumOfMultiplesOf(n, 5 * 3);
    }

    public static int sumOfMultiplesOf(int n, int number)
    {
        if(n < 1)
        {
            return 0;
        }
        final boolean isAMulipleOfNumber = (n - 1) % number == 0;
        if(isAMulipleOfNumber)
        {
            return (n - 1) + sumOfMultiplesOf( n - number, number );
        }
        else
        {
            return sumOfMultiplesOf( --n, number );
        }
    }
}
