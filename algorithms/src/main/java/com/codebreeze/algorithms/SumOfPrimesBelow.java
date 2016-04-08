package com.codebreeze.algorithms;

public class SumOfPrimesBelow
{
    public static long calculate(int max)
    {
        final boolean[] isPrime = new boolean[max + 1]; //wasting one space in order to get algorithm better reading
        //first assume all is true
        for(int i = 0; i < isPrime.length; i++)
        {
            isPrime[i] = true;
        }
        isPrime[0] = false;
        isPrime[1] = false; //primes have to be greater than 1
        isPrime[2] = true; // 2 is prime (only prime that is even)
        long sum = 2; //2 is the first prime, so set the sum to it as it is not in the loop
        for(int i = 3; i < isPrime.length; i += 2) //we have to go all the way through, not to square root of max here
        {
            if(isPrime[i]) //this is from initialization if true, if false, it has been checked.
            {
                isPrime[i] = isPrime(i); //this is the truth establised
                if(isPrime[i]) sum+=i; //all the ones left are marked as primes, correclty, so sum them
                for(int j = 2; i * j < isPrime.length; j++) //all multiples of this number are NOT primes!
                {
                    isPrime[i * j] = false;
                }
            }
        }
        return sum;
    }

    private static boolean isPrime(int number)
    {
        if(number == 2 || number == 3) return true;
        if(number < 2) return false;
        if(number % 2 == 0) return false;
        for(int i = 3; i * i < number; i+=2)
        {
            if(number % i == 0) return false;
        }
        return true;
    }
}
