package com.codebreeze.algorithms;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.lang.Math.ceil;
import static java.lang.Math.log10;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public class Math2
{
    private static final double LOG_10_2 = Math.log10(2);

    public static Set<Long> divisorsOf(final long n, final boolean includeOne, final boolean includeN)
    {
        return LongStream
                .rangeClosed(1, Math.round(Math.sqrt(n)) + 1) //by taking square root, we are eliminating repetition
                .filter(i -> n % i == 0) //only zero remainder divisors
                .mapToObj(i -> i == n/i ? asList(i) : asList(i, n/i)) // for numbers that are roots, only return 1 (6*6, 2*2, etc)
                .flatMap(Collection::stream)
                .filter(i -> i != 1 || includeOne) // exclude 1 as a divisor unless allowed
                .filter(i -> i != n || includeN) //exclude number itself unless allowed
                .collect(toSet());
    }

    public static int countSetBits(long n)
    {
        int count = 0;
        while (n != 0)
        {
            n &= (n-1) ;
            count++;
        }
        return count;
    }

    public static long gcd(final long a, final long b)
    {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    /*
    Detecting whether or not a given natural number n is a perfect power may be accomplished in many different ways,
    with varying levels of complexity. One of the simplest such methods is to consider all possible values for k across
    each of the divisors of n, up to k <= log_2 n. So if the divisors of n are n_1, n_2, ..., n_j then one of the
    values n_1^2, n_2^2, ..., n_j^2, n_1^3, n_2^3, ... must be equal to n if n is indeed a perfect power.

This method can immediately be simplified by instead considering only prime values of k. This is because if n = m^k
for a composite k = ap where p is prime, then this can simply be rewritten as n = m^k = m^(ap) = (m^a)^p. Because of
this result, the minimal value of k must necessarily be prime.
     */
    public static boolean isPerfectPower(final long n)
    {
        final long max = (long)(ceil(log10(n) / log10(2)));
        final Iterator<Integer> primes = PrimeNumbers.iterator();
        long k = 2;
        final Set<Long> divisors = Math2.divisorsOf(n, true, false);
        while(k <= max)
        {
            k = primes.next();
            for(final long divisor : divisors)
            {
                if(Power.apply(divisor, k) == n)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static Exponent findPerfectPower(final long n)
    {
        final long max = (long)(ceil(log10(n) / log10(2)));
        final Iterator<Integer> primes = PrimeNumbers.iterator();
        long k = 2;
        final Set<Long> divisors = Math2.divisorsOf(n, true, false);
        while(k <= max)
        {
            k = primes.next();
            for(final long divisor : divisors)
            {
                if(Power.apply(divisor, k) == n)
                {
                    final Exponent perfectPower = findPerfectPower(divisor);
                    if(perfectPower == null)
                    {
                        return Exponent.from(divisor, k);
                    }
                    else
                    {
                        return Exponent.from(perfectPower.base, k * perfectPower.power);
                    }
                }
            }
        }
        return null;
    }

    public static class Exponent {
        public long base;
        public long power;
        public Exponent(final long base, final long power)
        {
            this.base = base;
            this.power = power;
        }

        public static Exponent from(final long base, final long power)
        {
            return new Exponent(base, power);
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            Exponent exponent = (Exponent) o;
            return base == exponent.base && power == exponent.power;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(base, power);
        }

        @Override
        public String toString()
        {
            return base + "^" + power;
        }
    }

    public static <T> Set<List<T>> subsets(final List<T> sourceList) {
        int i, j;
        int bit;
        int max_bits;  // max bits for number i
        final Set<List<T>> result = new HashSet<>();
        final long numberOfSubsets = Power.apply(2, sourceList.size() ); // size - 1 to exclude first element
        for (i = 0; i < numberOfSubsets; i++)
        {
            final List<T> subset = new LinkedList<>();
            max_bits = (int)Math.floor( log2(i) );
            for (j = 0; j <= max_bits; j++)
            {
                bit = (i >> j) & 1;  // bit value at position j
                if (bit == 1)
                {
                    subset.add(sourceList.get(j));
                }
            }
            result.add(subset);
        }
        return result;
    }

    public static double log2(final double n)
    {
        return Math.log10(n) / LOG_10_2;
    }

    public static int product(final int[] values)
    {
        return Arrays.stream(values).reduce(1, (i, j) -> i * j);
    }

    public static int product(final int[] values, final int i, final int j)
    {
        return IntStream
                .rangeClosed(i, j).reduce(1, (product, b) -> values[b] * product);
    }
}
