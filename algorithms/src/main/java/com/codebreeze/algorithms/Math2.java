package com.codebreeze.algorithms;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;

import static java.lang.Math.ceil;
import static java.lang.Math.log10;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Math2
{
    public static List<Long> divisorsOf(final long n)
    {
        return LongStream
                .rangeClosed(1, Math.round(Math.sqrt(n)) + 1) //by taking square root, we are eliminating repetition
                .filter(i -> n % i == 0) //only zero remainder divisors
                .mapToObj(i -> i == n/i ? asList(i) : asList(i, n/i)) // for numbers that are roots, only return 1 (6*6, 2*2, etc)
                .flatMap(Collection::stream)
                .filter(i -> i != n) //exclude number itself
                .collect(toList());
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
        final List<Long> divisors = Math2.divisorsOf(n);
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
        final List<Long> divisors = Math2.divisorsOf(n);
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
}
