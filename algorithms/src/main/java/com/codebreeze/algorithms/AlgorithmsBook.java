package com.codebreeze.algorithms;

public class AlgorithmsBook
{
    /*
    1/3 (n -1)(n+1)
     */
    public static int problem_2_1(int n)
    {
        int r = 0;
        for(int i = 1; i <= n - 1; i++)
        {
            for( int j = i + 1; j <= n; j++)
            {
                for( int k = 1; k <= j; k++)
                {
                    r = r + 1;
                }
            }
        }
        return r;
    }

    /*
    n(n+1)(n+2)/3
     */
    public static int problem_2_2(int n)
    {
        int r = 0;
        for(int i = 1; i <= n; i++)
        {
            for( int j = 1; j <= i; j++)
            {
                for( int k = j; k <= (i + j); k++)
                {
                    r = r + 1;
                }
            }
        }
        return r;
    }

    /*
    n^2 (n+1)^2 / 8 + n (n+1)(2n+1)/12
     */
    public static int problem_2_3(int n)
    {
        int r = 0;
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= i; j++)
            {
                for(int k = j; k <= i + j; k++)
                {
                    for(int l = 1; l <= i + j - k; l++)
                    {
                        r = r + 1;
                    }
                }
            }
        }
        return r;
    }

    public static int problem_2_4(int n)
    {
        int r = 0;
        for(int i = 1; i <= n; i++)
        {
            for(int j = i + 1; j <= n; j++)
            {
                for(int k = i + j - 1; k <= n; k++)
                {
                        r = r + 1;
                }
            }
        }
        return r;
    }

    /**
     * evaluate polynomial f(x) = an xn + an-1 xn-1 + ... + a1 x + a0
     * there is a faster algorith called horner's method to evaluate polynomials
     * res = 0;
     for (i = n ; i >= 0;i--) {
     res = (res * x) + aᵢ;
     }
     return res;
     */
    public static int problem_2_5(int[] a, int x)
    {
        int p = a[0];
        int xpower = 1;
        for(int i = 1; i <= a.length; i++)
        {
            xpower = x * xpower;
            p = p + a[i] * xpower;
        }
        return p;
    }

    //find max
    public static int problem_2_6(int[] values)
    {
        int max = values[0];
        for(int i = 2; i < values.length; i++)
        {
            if(values[i] > max)
            {
                max = values[i];
            }
        }
        return max;
    }

    /**
     * prove that:
     * 1^2 - 2^2 + 3^2 - 4^2 ... + (-1)^(k - 1) k^2 = (-1)^(k-1) k(k+1)/2
     */
    public static long problem_2_32(int n)
    {
        int sign = (n - 1) % 2 == 0 ? 1 : -1;
        long sum = sign * n * (n + 1 );
        return sign;
    }

    /**
     * find the expression for the sum of the ith row of the following triangle. Each entry
     * is the sum of the three entries directly above it. All no existing entries are considered 0
     *                        1                = 1
     *                     1  1  1             = 3
     *                  1  2  3  2  1          = 9
     *               1  3  6  7  6  3  1       = 27
     *            1  4  10 16 19 16 10 4  1    = 81
     *
     *            3^(n-1)
     */
    public static long problem_2_33(int rowNumber)
    {
        return 0;
    }

    /*
    for i = 1 to n do
        for j=i to 2*i do
          output "foobar"

          express number of foobar outputs as function n:

          n(n+1)/2 + n
     */
    public static long problem_2_35(int n)
    {
        return 0;
    }

    /*
    for i = 0 to n/2 do
        for j=i to n-i do
          for k = 1 to j do
          output "foobar"

          express number of foobar outputs as function n:
            (n^3)/8
     */
    public static long problem_2_36(int n)
    {
        return 0;
    }

    /*
        we have 1000 data items to store on 1000 nodes. Each node can store copies of exactly
        three different items. Propose a replication scheme to minimize data loss as nodes fail.
        what is the expected number of data entries that get lost when three random nodes fail?
    */
    public static long problem_2_44(int n)
    {
        return 0;
    }

    /*
    Consider the following agorithm to find the minimum element in an array of numbers A[0,...,n].
    One extra variable tmp is allocated to hold the current minimum value. Start from A[0]; 'tmp' is
    compared against A[1], A[2], ..., A[N] in order. When A[i] < tmp, tmp = A[i]. What is the expected
    number of times that the assignment operation tmp = A[i] is performed.

    solution:
    The expected number of times the assignment to tmp is made is the sum of the probabilities that the
    nth element is the minimum. If we assume the minimum is distributed uniformly in our sequence then
    the probability any given element is the minimum is 1/n.

    Expected time is E(n) = E(n-1) + 1/n, E[1] = 0

    To compute expected value we sum this quantity for n:
        sum of 1 / i from i = 1 to i = n

and recognize this as the definition of the nth Harmonic number
 sum of 1 / i from i = 1 to i = n =~ ln n
H(n)=∑i=1n1i∼lnn

so our expected value approaches ln(n) as n grows large.
     */
    public static long problem_2_45(int i)
    {
        return 0;
    }

    /*
    you have a 100 story building and a couple of marbles. you must identify the lowest floor
    for which a marble will break if you drop it from this floor. How fast can you find this floor
    if you are given an infinite supply of marbles? what if you have only two marbles.
     */
    public static long problem_2_46(int i)
    {
        return 0;
    }

    /*
    suppose we start with n companies that eventually merge into one big company. How many different
    ways are there for them to merge?
     */
    public static long problem_2_49(int i)
    {
        return 0;
    }

    /*
    Ramanujam number can be written in two different ways as the sum of two cubes, i.e., there exist
    distinct a,b,c, and d such that a^3 + b^3 = c^3 + d^3. Generate all Ramanujam numbers where a,b,c,d < n.
     */
    public static long problem_2_50(int i)
    {
        return 0;
    }

    /*
    Six pirates must divide 300$ dollars among themselves. The division is to proceed as follows. The senior
    pirate proposes a way to divide the money. Then the pirates vote. If the senior pirate gets at least half
    the votes, he wins, and that division remains. If he does not, he is killed and then the next senior-most
    pirate gets a chanc to do the division. Now you have to tell what will happen and why?(i.e. how many pirates
    survive and how the division is done)? All the pirates are intelligent and the first priority is to stay
    alive and the next priority is to get as much money as possible.

    2.52: reconsider the pirate problem above where only one indivisible dollar is to to be divided. Who
    gets the dollar and how many are killed.
     */
    public static long problem_2_51(int i)
    {
        return 0;
    }
}

