package com.codebreeze.algorithms;

import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

/**
 * Largest prime factor
 * Problem 3
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * <p>
 * What is the largest prime factor of the number 600851475143 ?
 */
public class LargestPrimeFactor
{

    public static boolean isPrime(long num)
    {
        if (num < 2)
        {
            return false;
        }
        if (num == 2)
        {
            return true;
        }
        if (num % 2 == 0)
        {
            return false;
        }
        for (int i = 3; i * i <= num; i += 2)
        {
            if (num % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public static long calculate(long number)
    {
        return primeFactors(number)
                .stream()
                .mapToLong(i -> i)
//                .peek(System.out::println)
                .max()
                .getAsLong();
    }

    public static List<Long> primeFactors(final long n)
    {
        if (isPrime(n))
        {
            return singletonList(n);
        }
        else
        {
            final long factor = findAFactor(n);
            //check if this factor is prime, then add it, no further work on it, otherwise, break it even further to its primes
            return Stream
                    .of(isPrime(factor) ? asList(factor) : primeFactors(factor),
                            primeFactors(n / factor))
                    .flatMap(l -> l.stream())
                    .collect(toList());
        }
    }

    private static long findAFactor(long n)
    {
        return LongStream
                .range(2, n)
                .filter(i -> n % i == 0)
                .findAny()
                .getAsLong();
    }
}
