package com.codebreeze.algorithms.pearls2;

import java.util.function.IntPredicate;

public class IsPrime implements IntPredicate {
    @Override
    public boolean test(int n) {
        // all primes greater than 3 are of the form 6k ± 1, where k is any integer greater than 0.
        // This is because all integers can be expressed as (6k + i), where i = −1, 0, 1, 2, 3, or 4.
        // Primality test using 6k+-1 optimization
        if (n <= 3) {
            return n > 1;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        return true;
    }
}
