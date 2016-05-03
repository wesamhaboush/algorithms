package com.codebreeze.algorithms;

import java.math.BigInteger;
/*
Lexicographic permutations
Problem 24
A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:

012   021   102   120   201   210

What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class LexicographicPermutations
{
    /*
    Pk:=ϵ
    while S≠ϵ:
        f:=(|S|−1)!
        i:=⌊k/f⌋
        x:=S[i]
        k:=k mod f
        append x to Pk
        remove x from S
        return Pk
     */
    public static String calculate(final String s, final int k)
    {
        if(s.length() == 0)
        {
            return s;
        }
        else
        {
            final int factorial = factorial(s.length() - 1).intValueExact(); // find factorial of length
            final int index = k / factorial; // find index of first letter
            final char letter = s.charAt(index); // get that letter
            final int newK = k % factorial; //find how many permutations from this point we need to do
            final String newS = s.substring(0, index) + s.substring(index + 1); // remove the item from S to get new S
            //concatenate the letter we know, and repeat the whole thing for the next permutation set
            return letter + calculate(newS, newK);
        }
    }

    private static BigInteger factorial(final int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return BigInteger.valueOf(result);
    }
}
