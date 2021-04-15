package com.codebreeze.algorithms;

import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/*
1/2 2/4 = 4/8 4/8
3/5 6/7 = 21/35 18/35

26, 65 = 26/65 and 2/5 = 130 and 130

Digit cancelling fractions
Problem 33
The intFraction 49/98 is a curious intFraction, as an inexperienced mathematician in attempting to simplify it may incorrectly
 believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

There are exactly four non-trivial examples of this type of intFraction, less than one in value, and containing two digits
 in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 */
public class DigitCancellingFractions
{
    public static int calculate()
    {
        List<int[]> pairs = IntStream.range(10, 100)
                                     .filter(
                                             noTenMultipliers().and(noSingleDigits())
                                            )
                                     .mapToObj(toPair())
                                     .flatMap(Collection::stream)
                                     .collect(toList());
        final int[] product = pairs.stream()
                                  .reduce(new int[]{1, 1}, (int[] acc, int[] fraction) -> new int[]{
                                          acc[0] * fraction[0], acc[1] * fraction[1]
                                  });
//        System.out.println(Arrays.toString(product));
        final int gcd = (int)Math2.gcd(product[0], product[1]);
        final int[] result = new int[]{ product[0] / gcd, product[1] / gcd };
//        System.out.println(Arrays.toString(result));
//        pairs.stream().map(Arrays::toString).forEach(System.out::println);
//        System.out.println(pairs.size());
        return result[1];
    }

    private static IntPredicate noSingleDigits()
    {
        return i -> i > 10;
    }

    private static IntPredicate noTenMultipliers()
    {
        return i -> i % 10 != 0;
    }

    private static IntFunction<List<int[]>> toPair()
    {
        return i -> IntStream.range(i + 1, 100) //note i + 1 to make sure the intFraction is less than one
                             .filter(
                                     noTenMultipliers().and(noSingleDigits())
                                    )
                             //only pairs that have a similar digit
                             //find remainder and dividend of dividing by 10 for both digits, then find if there are
                             //intersections
                             // 28 and 82 (same number reversed) do not count, remove them
                             .mapToObj(j -> new int[]{i, j})
                             .filter(onlyWithNonEmptyDigitIntersection()
                                             .and(onlyNonReversedPairs())
                                    )
                             .filter(onlyDigitCancellingFractions())
                             .collect(toList());
    }

    private static Predicate<int[]> onlyNonReversedPairs()
    {
        return pair -> {
            final int[] firstDigits = new int[]{ pair[0] / 10, pair[0] % 10 };
            final int[] secondDigits = new int[]{ pair[1] / 10, pair[1] % 10 };
            return !(firstDigits[0] == secondDigits[1] && firstDigits[1] == secondDigits[0]);
        };
    }

    private static Predicate<int[]> onlyWithNonEmptyDigitIntersection()
    {
        return pair -> {
            final int[] firstDigits = new int[]{ pair[0] / 10, pair[0] % 10 };
            final int[] secondDigits = new int[]{ pair[1] / 10, pair[1] % 10 };
            return firstDigits[0] == secondDigits[0] || firstDigits[0] == secondDigits[1] ||
                   firstDigits[1] == secondDigits[0] || firstDigits[1] == secondDigits[1];
        };
    }

    private static Predicate<int[]> onlyDigitCancellingFractions()
    {
        return pair -> {
            final int[] firstDigits = new int[]{ pair[0] / 10, pair[0] % 10 };
            final int[] secondDigits = new int[]{ pair[1] / 10, pair[1] % 10 };
            if( firstDigits[0] == secondDigits[0] ) {
                //e.g.: 21 / 23 and 1 / 3, then compare 3 * 21 and 23 * 1
                return pair[0] * secondDigits[1] == pair[1] * firstDigits[1];
            }
            if( firstDigits[0] == secondDigits[1] ) {
                //e.g.: 21 / 32 and 1 / 3, then compare 3 * 21 and 32 * 1
                return pair[0] * secondDigits[0] == pair[1] * firstDigits[1];
            }
            if( firstDigits[1] == secondDigits[0] ) {
                //e.g.: 12 / 23 and 1 / 3, then compare 3 * 12 and 23 * 1
                return pair[0] * secondDigits[1] == pair[1] * firstDigits[0];
            }
            if( firstDigits[1] == secondDigits[1] ) {
                //e.g.: 12 / 32 and 1 / 3, then compare 3 * 12 and 32 * 1
                return pair[0] * secondDigits[0] == pair[1] * firstDigits[0];
            }
            throw new RuntimeException("should never have been there");
        };
    }

}
