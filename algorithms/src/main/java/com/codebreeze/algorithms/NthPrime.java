package com.codebreeze.algorithms;

/**
 * 10001st prime
 Problem 7
 By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

 What is the 10 001st prime number?
 */
public class NthPrime
{
    public static int calculate(int n)
    {
        if(n == 1)
        {
            return 2;
        }
        if(n == 3)
        {
            return 3;
        }
        int count = 2;
        int lastPrime = 3;
        while(count < n)
        {
            while(!LargestPrimeFactor.isPrime(lastPrime+=2))
            {
            }
            count++;
        }
        return lastPrime;
    }
}
