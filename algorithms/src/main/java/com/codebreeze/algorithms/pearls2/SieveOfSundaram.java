package com.codebreeze.algorithms.pearls2;

import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;
import com.codebreeze.algorithms.primitive.collections.list.IntList;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class SieveOfSundaram implements IntPredicate {
    @Override
    public boolean test(int n) {
        IntList primes = primes(n);

        return primes.contains(n);
    }

    private IntList primes(int n) {
        // In general Sieve of Sundaram, produces
        // primes smaller than (2*x + 2) for a number
        // given number x. Since we want primes
        // smaller than n, we reduce n to half
        int nNew = (n - 1) / 2;

        // This array is used to separate numbers of the
        // form i+j+2ij from others where 1 <= i <= j
        boolean[] marked = new boolean[nNew + 1];

        // Initialize all elements as not marked
        Arrays.fill(marked, false);

        // Mark all numbers of the form i + j + 2ij as true where 1 <= i <= j
        for (int i = 1; i <= nNew; i++) {
            for (int j = i; (i + j + 2 * i * j) <= nNew; j++) {
                marked[i + j + 2 * i * j] = true;
            }
        }

        IntList primes = new ArrayIntList();

        // Since 2 is a prime number
        if (n >= 2) {
            primes.add(2);
        }

        // add remaining primes are of the form 2*i + 1 such that marked[i] is false.
        for (int i = 1; i <= nNew; i++) {
            if (!marked[i]) {
                primes.add(2 * i + 1);
            }
        }

//        System.out.println(
//            n + ":" + primes.stream()
//                .mapToObj(String::valueOf)
//                .collect(joining(","))
//        );
        return primes;
    }
}
