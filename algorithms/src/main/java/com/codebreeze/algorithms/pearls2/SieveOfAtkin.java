package com.codebreeze.algorithms.pearls2;

import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;
import com.codebreeze.algorithms.primitive.collections.list.IntList;

import java.util.function.IntFunction;

public class SieveOfAtkin implements IntFunction<int[]> {

    @Override
    public int[] apply(int max) {
        int limit = max + 1;
        IntList primes = new ArrayIntList();
        // 2 and 3 are known to be prime
        if (limit > 2) {
            primes.add(2);
//            System.out.print(2 + " ");
        }

        if (limit > 3) {
            primes.add(3);
//            System.out.print(3 + " ");
        }

        // Initialise the sieve array with
        // false values
        boolean[] sieve = new boolean[limit];

        for (int i = 0; i < limit; i++) {
            sieve[i] = false;
        }

        /* Mark sieve[n] is true if one of the
        following is true:
        a) n = (4*x*x)+(y*y) has odd number
           of solutions, i.e., there exist
           odd number of distinct pairs
           (x, y) that satisfy the equation
           and    n % 12 = 1 or n % 12 = 5.
        b) n = (3*x*x)+(y*y) has odd number
           of solutions and n % 12 = 7
        c) n = (3*x*x)-(y*y) has odd number
           of solutions, x > y and n % 12 = 11 */
        for (int x = 1; x * x < limit; x++) {
            for (int y = 1; y * y < limit; y++) {

                // Main part of Sieve of Atkin
                int n = (4 * x * x) + (y * y);
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
                    sieve[n] ^= true;
                }

                n = (3 * x * x) + (y * y);
                if (n <= limit && n % 12 == 7) {
                    sieve[n] ^= true;
                }

                n = (3 * x * x) - (y * y);
                if (x > y && n <= limit && n % 12 == 11) {
                    sieve[n] ^= true;
                }
            }
        }

        // Mark all multiples of squares as non-prime
        for (int r = 5; r * r < limit; r++) {
            if (sieve[r]) {
                for (int i = r * r; i < limit; i += r * r) {
                    sieve[i] = false;
                }
            }
        }

        // Print primes using sieve[]
        for (int a = 5; a < limit; a++) {
            if (sieve[a]) {
                primes.add(a);
//                System.out.print(a + " ");
            }
        }
        return primes.stream().toArray();
    }
}
