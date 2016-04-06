package com.codebreeze.algorithms;

import java.util.stream.IntStream;

/**
 * Largest palindrome product
 * Problem 4
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * <p>
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class LargestPalindromeProduct
{
    public static int calculate(int n)
    {
        // get the maximum number composed of n digits.
        // for example: n = 2, then highestNumberWithNDigits = 99,
        // if n == 4, then highestNumberWithNDigits = 9999
        final int highestNumberWithNDigits = IntStream
                .range(0, n)
                .reduce(0, (accumulator, item) -> accumulator += 9 * (int)Math.pow(10, item));
        //get the lower number composed of n digits
        //for example, n = 2, then lowerNumberWithNDigits = 10
        //if n == 4, then lowestNumberWithNDigits = 1000
        final int lowestNumberWithNDigits = (int)Math.pow(10, n - 1);
//                IntStream
//                .range(0, n)
//                .reduce(0, (accumulator, item) -> accumulator += 1 * (int)Math.pow(10, item));
        final int highestPalindrome = highestNumberWithNDigits * highestNumberWithNDigits; // get max, like: 99 * 99 will be maximum product.
        final int lowestPalindrome = lowestNumberWithNDigits * lowestNumberWithNDigits;

        int index = highestPalindrome;
        while(index >= lowestPalindrome)
        {
            if(isPalindrome(asDigits(index)))
            {
                //let's find its factors
                int firstFactor = lowestNumberWithNDigits;
                while (firstFactor <= highestNumberWithNDigits)
                {
                    final int secondFactor = index / firstFactor;
                    final int remainder = index % firstFactor;
                    if(remainder == 0 && asDigits(secondFactor).length == n)
                    {
                        System.out.println(String.format("palindrome[%d], factors[%d,%d]", index, firstFactor, secondFactor));
                        return index;
                    }
                    firstFactor++;
                }
            }
            index--;
        }
        //now go ascendingly finding palindromes
        //        final int lowestNumberWithNDigits = minNumberWithNDigits(n);
        //strategy: get a palindrum, then factor it to two 3 digits n
        //write a method that will check the n is a palindrum.
        return -1;
    }

    public static int[] asDigits(int number)
    {
        final int size = (int) Math.floor(1 + Math.log10(number));
        final int[] result = new int[size];
        int index = 0;
        while (number > 0)
        {
            result[index++] = number % 10; //get this digit as a remainer
            number = number / 10; //remove that digit (like shift right)
        }
        return result;
    }

    public static boolean isPalindrome(int[] digits)
    {
        final int size = digits.length;
        int left = 0;
        int right = size - 1;
        while (left < right)
        {
            if (digits[left] != digits[right])
            {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
