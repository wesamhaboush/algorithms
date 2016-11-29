package com.codebreeze.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/*
Lehmer Code algorithm:

convert permutation sigma = 3 4 1 2 to decimal representation

Step 1: let i = min(sigma) // 3 4 |1| 2
Step 2: count the number of digits to the left of digit i that is greater than sigma(i). This is the first
digit of the factoradic:
 (3 4) |1| 2 //note: 3 and 4 are greater than 1, so we have 2 numbers (3 and 4) as the outcome of step 2.
Step 3: remove sigma(i) from the permutation, to have left:
 3 4 2
Step 4: perform steps 1 to 3 until no digits remain. Lets do that:

 3 4 |1| 2 -> (3 4) |1| 2 -> Num of digits greater than 1 to its left: 2
 3 4 |2|   -> (3 4) |2|   -> Num of digits greater than 2 to its left: 2
 |3| 4     -> |3| 4       -> Num of digits greater than 3 to its left: 0
 |4|       -> |4|         -> Num of digits greater than 4 to its left: 0

Step 5: convert factoradic 2,2,0,0 to decimal using the factorial numbers as the place for each digit:

Place:  3!        2!        1!        0!
digit:  2         2         0         0
       (3!)(2) + (2!)(2) + (1!)(0) + (0!)(0) = 12 + 4 + 0 + 0 = 16

16 is the unique lehmer code for the permutation 3 4 1 2
 */
public class FactoradicPermutation
{
    //fact: there are n! permutation for n elements.
    /*
    For i = 0 to n! - 1:
 *    Represent i in factoradic base.
 *    Convert this representation to a Lehmer code.
 *    Generate the permutation based on this Lehmer code.
     */
    public static <T> Iterator<List<T>> of(final List<T> ts)
    {
        final int size = ts.size();
        return IntStream.range(0, factorialInt(size))
                .map(FactoradicPermutation::numberToFactoradic)
                .mapToObj(i -> factoradicToLehmerCode(i, size))
                        .map(i -> FactoradicPermutation.lehmerCodeToPermutation(i, size))
                .map(indicesToList(ts))
                .iterator();
    }

    private static <T> Function<int[], List<T>> indicesToList(final List<T> ts)
    {
        return indices -> IntStream
                .range(0, indices.length)
                .mapToObj(i -> ts.get(indices[i]))
                .collect(toList());
    }

    public static int[] lehmerCodeToPermutation(final int[] lehmerCode, int size)
    {
        int[] indices = IntStream.range(0, size).toArray();
        final int[] result = new int[size];
        for(int i = 0; i < lehmerCode.length; i++)
        {
            final int permutationElementIndex = indices[lehmerCode[i]];
            result[i] = permutationElementIndex; // each lehmer digit indicates the index out of the items left
            int[] newIndices = Arrays.stream(indices).filter(index -> index != permutationElementIndex).toArray();
            indices = newIndices;
        }
        return result;
    }

    /*
    just add zeros to the beginning, and copy the rest in place
     */
    public static int[] factoradicToLehmerCode(final int number, final int size)
    {
        final int[] unpaddedFactoradicDigits = numberToDigits(number);
        int[] sequence = new int[size];
        System.arraycopy(
                unpaddedFactoradicDigits, //src
                0, //src start position
                sequence, //destination
                size - unpaddedFactoradicDigits.length, //destination start position
                unpaddedFactoradicDigits.length); //how many items to copy
        return sequence;
    }

    public static int[] numberToDigits(final int number)
    {
        int temp = number;
        final List<Integer> array = new ArrayList<>();
        do
        {
            array.add(0, temp % 10);
            temp /= 10;
        } while (temp > 0);
        return array
                .stream()
                .mapToInt(i -> i)
                .toArray();
    }

    public static int digitsToNumber(final int[] digits)
    {
        int result = 0;
        int multiplier = 1;
        for(int i = digits.length - 1; i >=0; i--)
        {
            result += digits[i] * multiplier;
            multiplier *= 10;
        }
        return result;
    }

    public static int numberToFactoradic(final int number)
    {
        int dividend = number;
        int multiplier = 1;
        int result = 0;
        int divisor = 1;
        while (dividend != 0)
        {
            final int remainder = dividend % divisor; // find the remainder
            dividend = dividend / divisor; // find the number we'll use for next iteration
            result += remainder * multiplier; // add the remainder in the right position in the result (which digit?)
            multiplier *= 10; // move the multiplier to next digit position by multiplying by 10;
            divisor++; // we divide by increasing numbers (1, 2, 3, etc until the dividend is 0
        }
        return result;
    }

    private static long factorial(long n)
    {
        if (n == 1)
        {
            return 1;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }

    private static int factorialInt(int n)
    {
        return (int)factorial(n);
    }

}
