package com.codebreeze.algorithms;

import java.util.*;
import java.util.stream.LongStream;

import static com.codebreeze.algorithms.LargestPrimeFactor.primeFactors;

/**
 * Smallest multiple
 Problem 5
 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

 What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class SmallestMultipleOfNumbers
{
    public static long calculate(int n)
    {
        final long[] values = LongStream
                .rangeClosed(2, n)
                .toArray(); //start from 2 cz 1 is an identity element, no need to work on it.
        return factors(values) //two is the first prime factor to consider
                .stream()
                .reduce(1l, (i, j) -> i * j);
    }

    /**
     * this method will return the lowest common denominator for all the numbers in the values array
     * Note all the values returned are prime factors!
     */
    private static List<Long> factors(final long[] values)
    {
        //find first element with value that is not 1
        final OptionalLong optionalFirstElementNotOne = Arrays
                .stream(values)
                .filter(i -> i != 1)
                .findFirst();
        if(optionalFirstElementNotOne.isPresent())
        {
            final long firstElementNotOne = optionalFirstElementNotOne.getAsLong();
            //find prime factors of the first non one element
            final List<Long> primeFactors = primeFactors(firstElementNotOne);
            final List<Long> results = new ArrayList<>();
            //divide all divideable elements by each of those prime factors
            for(long primeFactor : primeFactors)
            {
                for(int i = 0; i < values.length; i++)
                {
                    values[i] = values[i] % primeFactor == 0
                                ? values[i] / primeFactor
                                : values[i]; //only factor if it has no remainder
                }
            }
            //all these prime factors to items to multiply for the smallest common multiplier
            results.addAll(primeFactors);
            //now move to the next prime factor
            results.addAll(factors(values));
            return results;
        }
        else
        {
            return Collections.emptyList();
        }
    }
}
