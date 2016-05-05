package com.codebreeze.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class PrimeNumbers
{
    private static final List<Integer> FOUND_PRIMES =
            new ArrayList<Integer>()
            {
                {
                    add(2);
                    add(3);
                }
            };

    public static Iterable<Integer> iterable(){
        return () -> iterator();
    }

    public static Iterator<Integer> iterator()
    {
        return new Iterator<Integer>()
        {
            private int lastReturnedPrimeIndex = -1;

            @Override
            public boolean hasNext()
            {
                return true;
            }

            @Override
            public Integer next()
            {
                if(lastReturnedPrimeIndex < (FOUND_PRIMES.size() - 1))
                {
                    return FOUND_PRIMES.get(++lastReturnedPrimeIndex); //increment the index, and return its value
                }
                else
                {
                    //find the next prime, and add it to the list, then return it.
                    final int lastFoundPrime = FOUND_PRIMES.get(lastReturnedPrimeIndex);
                    final int nextPrime = nextPrime(lastFoundPrime);
                    //you want to avoid multiple threads adding the same prime to the list out of order
                    synchronized (FOUND_PRIMES)
                    {
                        // we are still the last found prime,
                        // then add, otherwise, seems someone was faster and added the next one, so do nothing
                        if(lastReturnedPrimeIndex == (FOUND_PRIMES.size() - 1))
                        {
                            FOUND_PRIMES.add(nextPrime);
                        }
                    }
                    return FOUND_PRIMES.get(++lastReturnedPrimeIndex);
                }
            }
        };
    }

    private static int nextPrime(final int lastFoundPrime)
    {
        int nextCandidatePrime = lastFoundPrime + 2;
        while(true)
        {
            if(isPrime(nextCandidatePrime))
            {
                return nextCandidatePrime;
            }
            else
            {
                nextCandidatePrime += 2; //even numbers are NOT primes except 2
            }
        }
    }

    public static boolean isPrime(long number)
    {
        if(number == 2 || number == 3) return true;
        if(number < 2) return false;
        if(number % 2 == 0) return false;
        for(int i = 3; i * i <= number; i+=2)
        {
            if(number % i == 0) return false;
        }
        return true;
    }

    public static List<Long> primeFactors(final long n)
    {
        if(n == 1)
        {
            return emptyList();
        }
        else if (isPrime(n))
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
