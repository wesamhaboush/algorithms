package com.codebreeze.algorithms;

import java.util.Iterator;
import java.util.function.Function;

/*
Quadratic primes
Problem 27
Euler discovered the remarkable quadratic formula:

n² + n + 41

It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.

The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.

Considering quadratics of the form:

n² + an + b, where |a| < 1000 and |b| < 1000

where |n| is the modulus/absolute value of n
e.g. |11| = 11 and |−4| = 4
Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.


 */
public class QuadraticPrimes
{
    /*
    b itself has to be prime in order for f(0) to be prime, so this will allow us
    to know righ away if this series is good enough
     */
    public static int calculate(final int aMax, final int bMax)
    {
        final int[][] signs = {{-1,-1}, {-1, 1}, {1, -1}, {1, 1}};
        int maxA = 0;
        int maxB = 0;
        int maxN = 0;
        //we are starting a from 1 because a = 0 will give an even number when n = 1, which is not prime
        for(int a = 1; a < aMax; a++)
        {
            Iterator<Integer> primes = PrimeNumbers.iterator();
            for(int b = primes.next(); b < bMax; b = primes.next())
            {
                for(int i = 0; i < signs.length; i++)
                {
                    final int signedA = signs[i][0] * a;
                    final int signedB = signs[i][1] * b;
                    final int newN = findLength(signedA, signedB);
                    if (newN > maxN)
                    {
                        maxA = signedA;
                        maxB = signedB;
                        maxN = newN;
                    }
                }
            }
        }
        System.out.println("max A = " + maxA);
        System.out.println("max B = " + maxB);
        System.out.println("max N = " + maxN);
        return maxA * maxB;
    }

    private static int findLength(final int a, final int b)
    {
        final Function<Integer, Integer> f = n -> n * n + a * n + b;
        int n = -1;
        while(PrimeNumbers.isPrime(f.apply(++n))); //increment while the results are prime;
        return n;
    }
}
