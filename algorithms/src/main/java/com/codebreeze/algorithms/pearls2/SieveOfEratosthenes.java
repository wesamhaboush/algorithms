package com.codebreeze.algorithms.pearls2;

import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;
import com.codebreeze.algorithms.primitive.collections.list.IntList;

import java.util.Arrays;
import java.util.function.IntFunction;

public class SieveOfEratosthenes implements IntFunction<IntList> {

    // This method finds all primes smaller than 'max'
    // using simple sieve of eratosthenes. It also stores
    // found primes in list prime list
    @Override
    public IntList apply(int max) {
        // Create a boolean array "mark[0..n-1]" and initialize
        // all entries of it as true. A value in mark[p] will
        // finally be false if 'p' is Not a prime, else true.
        boolean[] mark = new boolean[max + 1];

        Arrays.fill(mark, true);

        for (int p = 2; p * p < max; p++) {
            // If p is not changed, then it is a prime
            if (mark[p]) {
                // Update all multiples of p
                for (int i = p * p; i < max; i += p) {
                    mark[i] = false;
                }
            }
        }

        // add found prime numbers
        IntList primes = new ArrayIntList();
        for (int p = 2; p < max; p++) {
            if (mark[p]) {
                primes.add(p);
            }
        }
        return primes;
    }
}
