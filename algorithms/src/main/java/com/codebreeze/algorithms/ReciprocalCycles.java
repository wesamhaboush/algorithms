package com.codebreeze.algorithms;

import java.math.BigInteger;

/*
Reciprocal cycles
Problem 26
A unit intFraction contains 1 in the numerator. The decimal representation of the unit fractions with
denominators 2 to 10 are given:

1/2	= 	0.5
1/3	= 	0.(3)
1/4	= 	0.25
1/5	= 	0.2
1/6	= 	0.1(6)
1/7	= 	0.(142857)
1/8	= 	0.125
1/9	= 	0.(1)
1/10	= 	0.1
Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle.
It can be seen that 1/7 has a 6-digit recurring cycle.

Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal intFraction part.
 */
public class ReciprocalCycles
{

    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger FIVE = BigInteger.valueOf(5);

    /*
            Find the first number of the form 10**k - 1 that divides exactly by the denominator of the intFraction,
            divide it by the denominator and multiply by the numerator and you get your repeating part.
             */
    public static BigInteger[] calculate(final BigInteger maxN)
    {
        BigInteger n = TWO;
        BigInteger maxPeriod = BigInteger.ZERO;
        BigInteger repeatingPart = BigInteger.ZERO;
        for(BigInteger i = TWO; i.compareTo(maxN) < 0; i = i.add(BigInteger.ONE))
        {
            final BigInteger noneTerminatingPart = removeTwosAndFives(i);
            if(noneTerminatingPart != BigInteger.ONE) // still there is primes that will repeat
            {
                final BigInteger[] periodAndItsValue = findPeriod(noneTerminatingPart);
                maxPeriod = maxPeriod.compareTo(periodAndItsValue[0]) == 1 ? maxPeriod : periodAndItsValue[0];
                repeatingPart = maxPeriod == periodAndItsValue[0] ? periodAndItsValue[1] : repeatingPart;
                n = maxPeriod == periodAndItsValue[0] ? i : n;
            }
        }
        return new BigInteger[]{n, maxPeriod, repeatingPart};
    }

    private static BigInteger removeTwosAndFives(BigInteger i)
    {
        //faster: eliminate all tens!
        while(i.remainder(BigInteger.TEN) == BigInteger.ZERO)
        {
            i = i.divide(BigInteger.TEN);
        }
        //eliminate 2s
        while( i.remainder(TWO) == BigInteger.ZERO)
        {
            i = i.divide(TWO);
        }
        //eliminate fives
        while(i.remainder(FIVE) == BigInteger.ZERO)
        {
            i = i.divide(FIVE);
        }
        return i;
    }

    /*
    Let n < d, and you're trying to figure out the repeating part of n/d. Let p be the number of digits in
    the repeating part: then n/d = R * 10^(-p) + R * 10^(-2p) + ... = R * ((10^-p)^1 + (10^-p)^2 + ...).
    The bracketed part is a geometric series, equal to 1/(10^p - 1).

So n / d = R / (10^p - 1). Rearrange to get R = n * (10^p - 1) / d. To find R, loop p from 1 to infinity,
and stop as soon as d evenly divides n * (10^p - 1).

returns the period K, and the repeating part (R)
     */
    private static BigInteger[] findPeriod(final BigInteger D) // assume n = 1 in the equations above
    {
        BigInteger d = removeTwosAndFives(D);
        BigInteger k = BigInteger.ONE;
        BigInteger z = Power.apply(BigInteger.TEN,k).subtract(BigInteger.ONE);
        while( z.remainder(d) != BigInteger.ZERO)
        {
            k = k.add(BigInteger.ONE);
            z = Power.apply(BigInteger.TEN,k).subtract(BigInteger.ONE);
        }
        return new BigInteger[]{k, z.divide(d) };
    }
}
