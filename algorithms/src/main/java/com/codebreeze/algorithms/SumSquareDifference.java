package com.codebreeze.algorithms;

/**
 * Sum square difference
 Problem 6
 The sum of the squares of the first ten natural numbers is,

 12 + 22 + ... + 102 = 385
 The square of the sum of the first ten natural numbers is,

 (1 + 2 + ... + 10)2 = 552 = 3025
 Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

 Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class SumSquareDifference
{
    public static int calculate(final int n)
    {
        //sum of n =  n(n+1) / 2, i.e. (sum of n)^2 = ((n(n+1))^2)/4
        //sum of n^2 = (1/3)*n^3 + (1/2)*n^2 + n / 6
        //does this produce the one blow? may be?
        return (n*n-1)*(3*n+2)*n/12; // what is the source of this formula?
    }
}
