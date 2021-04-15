package com.codebreeze.algorithms;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

/*
Non-abundant sums
Problem 23
A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28,
which means that 28 is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and it is called
abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written
as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers
greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot
be reduced any further by analysis even though it is known that the greatest number that cannot be
expressed as the sum of two abundant numbers is less than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public class NonAbundantSums
{

    public static long calculate(final int n)
    {
        final Set<Integer> abundants = IntStream.rangeClosed(1, n)
                                                .filter(NonAbundantSums::isAbundant)
                                                .boxed()
                                                .collect(toCollection(TreeSet::new));

        long sum = 0;
        for(int i = 0; i < n; i++)
        {
            if(!isSumOfTwoAbundants(abundants, i + 1))
            {
                sum += (i+1);
            }
        }
        return sum;
    }

    private static boolean isSumOfTwoAbundants(final Set<Integer> abundants, int n)
    {
        for (int i : abundants)
        {
            if (i > n)
            {
                return false; //we already crossed, we need to exit
            }
            if (abundants.contains(n - i))
            {
                return true; //an abundant, with complemented by another is found
            }
        }
        return false;
    }

    private static void markMultipliersOfAbundant(int number, boolean[] isAbundant)
    {
        //we start from 1 to take multiples, if we start from 1, we take both abundant and multipliers of it, which is not the intention
        for(int i = 2; i * number < isAbundant.length + 1; i++)
        {
            isAbundant[(i * number) - 1] = true;
        }
    }

    private static boolean isAbundant(final int n)
    {
        if(n < 12)
        {
            return false;
        }
        return sumOfProperDivisors(n) > n;
    }

    private static long sumOfProperDivisors(final long n)
    {
        final long sumOfDivisors = Sigma.of(n);
        final long sumOfProperDivisors = sumOfDivisors - n;
        return sumOfProperDivisors;
    }
}
