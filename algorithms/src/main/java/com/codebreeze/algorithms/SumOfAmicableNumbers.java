package com.codebreeze.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class SumOfAmicableNumbers
{
    private static long sum(final List<Long> longs)
    {
        return longs.stream()
                   .mapToLong(i -> i)
                   .sum();
    }

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

    /*
    euler's amicable numbers rule
     */
    private static boolean isAmicable(final long n)
    {
        final List<Long> nList = new ArrayList<>(divisorsOf(n));
        final long nSum = sum(nList);
        final List<Long> mList = new ArrayList<>(divisorsOf(nSum));
        final long mSum = sum(mList);
        return mSum == n && mSum != nSum;
    }

    public static long calculate(final int n)
    {
        return IntStream.rangeClosed(2, n) //1 is not amicable, do not use it
                        .filter(SumOfAmicableNumbers::isAmicable)
                        .mapToLong(i -> i)
//                        .peek(System.out::println)
                        .sum();
    }

}
