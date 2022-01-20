package com.codebreeze.algorithms.pearls2;

import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;
import com.codebreeze.algorithms.primitive.collections.list.IntList;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class SegmentedSieveOfEratosthenes implements IntPredicate {
    private static final IntList PRIMES_CACHE = new ArrayIntList();
    private final boolean cacheEnabled;
    private final SieveOfEratosthenes sieveOfEratosthenes;

    public SegmentedSieveOfEratosthenes(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
        this.sieveOfEratosthenes = new SieveOfEratosthenes();
    }

    // Prints all prime numbers smaller than 'n'
    public IntList segmentedSieve(int n) {
        // Compute all primes smaller than or equal
        // to square root of n using simple sieve
        int interval = (int) (StrictMath.floor(StrictMath.sqrt(n)) + 1);
        IntList primes = primes(interval);
        if (primes.size() == 0) {
            primes.add(2); // minimum is one element
        }
        // Divide the range [0..n-1] in different segments
        // We have chosen segment size as sqrt(n).
        int low = primes.get(primes.size() - 1);
        int high = low + interval;

        // While all segments of range [0..n-1] are not processed,
        // process one segment at a time
        while (low < n) {
            if (high >= n) {
                high = n;
            }

            // To mark primes in current range. A value in mark[i]
            // will finally be false if 'i-low' is Not a prime,
            // else true.
            boolean[] mark = new boolean[interval + 1];

            Arrays.fill(mark, true);

            // Use the found primes by simpleSieve() to find
            // primes in current range
            for (int i = 0; i < primes.size(); i++) {
                // Find the minimum number in [low..high] that is
                // a multiple of prime.get(i) (divisible by prime.get(i))
                // For example, if low is 31 and prime.get(i) is 3,
                // we start with 33.
                int loLim = (int) (StrictMath.floor((double) low / (double) primes.get(i)) * primes.get(i));
                if (loLim < low) {
                    loLim += primes.get(i);
                }
                /*  Mark multiples of prime.get(i) in [low..high]:
                    We are marking j - low for j, i.e. each number
                    in range [low, high] is mapped to [0, high-low]
                    so if range is [50, 100]  marking 50 corresponds
                    to marking 0, marking 51 corresponds to 1 and
                    so on. In this way we need to allocate space only
                    for range  */
                for (int j = loLim; j < high; j += primes.get(i)) {
                    mark[j - low] = false;
                }
            }

            // Numbers which are not marked as false are prime
            for (int i = low; i < high; i++) {
                if (mark[i - low]) {
                    primes.add(i);
                }
            }

            // Update low and high for next segment
            low = low + interval;
            high = high + interval;
        }
        return primes;
    }

    private IntList primes(int interval) {
        return cacheEnabled
            ? PRIMES_CACHE
            : sieveOfEratosthenes.apply(interval);
    }

    @Override
    public boolean test(int number) {
        IntList primes = segmentedSieve(number + 1);
        return primes.contains(number);
    }
}
