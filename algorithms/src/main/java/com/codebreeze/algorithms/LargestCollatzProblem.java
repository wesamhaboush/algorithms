package com.codebreeze.algorithms;

import java.util.HashMap;
import java.util.Map;

/*
Longest Collatz sequence
Problem 14
The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.
 */
public class LargestCollatzProblem
{
    private static final Map<Long, Long> startingPointToChainLength = new HashMap<Long, Long>(){
        {
            put(1L, 0L); // 1 has a chain of zero length
        }
    };

    public static long[] calculate(long n)
    {
        if(n == 2)
        {
            return new long[]{1, 0};
        }

        long startingPointWithMaxChainLength = 0;
        long maxChainLength = 0;
        for(long i = 2; i < n; i++)
        {
            long chainLength = 0; // 1 is the minimum which is when we are at 1 (last item in the chain).
            long startingPoint = i;
            while(startingPoint != 1) //as long as we have not hit the one, keep going
            {
                if(startingPointToChainLength.containsKey(startingPoint))
                {
                    chainLength = chainLength + startingPointToChainLength.get(startingPoint); //memoization for speed
                    startingPoint = 1; //given we now know the length, simulate us going to the end.
                }
                else
                {
                    startingPoint = (startingPoint % 2 == 0) ? (startingPoint / 2) : (3 * startingPoint + 1);
                    chainLength++;
                }
            }
            startingPointToChainLength.put(i, chainLength);
            if(chainLength > maxChainLength)
            {
                startingPointWithMaxChainLength = i;
                maxChainLength = chainLength;
            }
        }
        return new long[]{startingPointWithMaxChainLength, maxChainLength};
    }
}
