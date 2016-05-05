package com.codebreeze.algorithms;
/*
Number spiral diagonals
Problem 28
Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

21 22 23 24 25
20  7  8  9 10
19  6  1  2 11
18  5  4  3 12
17 16 15 14 13

It can be verified that the sum of the numbers on the diagonals is 101.

What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 */
public class NumberSpiralDiagonals
{
    /*
    The solution will be to skip first 1 element 4 times, then 3 elements 4 times,
    then 5 elements 4 times, etc untill you have finished the n * n elements.
    so:
    1 +
    2 (skip) + 3 + 4 (skip) + 5 + 6 (skip) + 7 + 8 (skip) + 9 +
    10 (skip) + 11 (skip) + 12 (skip) + 13 + 14 (skip) + 15 (skip) + 16 (skip) + 17 etc

    so it looks like
    sum
    skip, sum, skip, sum, skip, sum, skip, sum
    skip, skip, skip, sum, skip, skip, skip, sum, skip, skip, skip, sum, skip, skip, skip, sum,
    skip, skip, skip, skip, skip, sum, skip, skip, skip, skip, skip, sum, skip, skip, skip, skip, skip, sum ...


     */
    public static long calculate(final long n)
    {
        long stepSize = 2;
        long sum = 1; //started with center in count
        long count = 2;
        long total = n * n;
        while(count <= total)
        {
            count = moveDown(stepSize - 1, count);
            sum += count;
            count = moveLeft(stepSize, count);
            sum += count;
            count = moveUp(stepSize, count);
            sum += count;
            count = moveRight(stepSize, count);
            sum += count;
            stepSize = nextStepSize(stepSize);
            count = nextCircleStart(count); //start a circle
        }
        return sum;
    }

    private static long nextCircleStart(final long count)
    {
        return count + 1;
    }

    private static long moveDown(final long stepSize, final long count)
    {
        return count + stepSize;
    }

    private static long moveLeft(final long stepSize, final long count)
    {
        return count + stepSize;
    }

    private static long moveUp(final long stepSize, final long count)
    {
        return count + stepSize;
    }

    private static long moveRight(final long stepSize, final long count)
    {
        return count + stepSize;
    }

    private static long nextStepSize(final long stepSize)
    {
        return stepSize + 2;
    }
}
