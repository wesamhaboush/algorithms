package com.codebreeze.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Special Pythagorean triplet
 * Problem 9
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * <p>
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * <p>
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class SpecialPythogoreanTriplet
{
    /**
     * The German monk and mathematician Michael Stifel published this method in 1544
     */
    public static int calculate(int sum)
    {
        //create the three variables for achieve the
        int whole = 1;
        int numerator = 1;
        int denominator = 3;
        int improperDenominator = whole * denominator + numerator;
        int a = improperDenominator;
        int b = denominator;
        int c = Math.max(a, b) + 1;
        while ((a + b + c) != sum)
        {
            whole++;
            numerator++;
            denominator += 2;
            improperDenominator = whole * denominator + numerator;
            a = improperDenominator;
            b = denominator;
            c = Math.max(a, b) + 1;
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return a * b * c;
    }

    /**
     * Leonard Eugene Dickson (1920)[6] attributes to himself the following method for generating Pythagorean triples
     */
    public static int calculate2(final int sum)
    {
        int x, y, z, r, s, t;
        x = y = z = r = s = t = 0;
        while (r < sum) //did not know what to limit it by. If we got that far, most like we are lost
        {
            if( ((r * r) % 2) == 0)
            {
                final int[][] factorPairSet = findFactors(r * r / 2);
                for (int[] factorPair : factorPairSet)
                {
                    s = factorPair[0];
                    t = factorPair[1];
                    x = r + s;
                    y = r + t;
                    z = r + s + t;
                    if (x + y + z == sum)
                    {
                        return x * y * z;
                    }
                }
            }
            r++;
        }
        return -1;
    }

    private static int[][] findFactors(int number)
    {
        int i = 1;
        List<int[]> factors = new ArrayList<>();
        while(i < number)
        {
            if( number % i == 0){
                factors.add(new int[]{i, number / i});
            }
            i++;
        }
        return factors.toArray(new int[factors.size()][]);
    }
}
