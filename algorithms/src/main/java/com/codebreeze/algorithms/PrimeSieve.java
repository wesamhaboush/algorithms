package com.codebreeze.algorithms;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class PrimeSieve
{

    int[] primes;
    IntegerRange sieveRange;
    IntegerRange primeRange;

    /**
     * Constructs a prime sieve for the integer range [1,n].
     *
     * @param n The upper bound of the range to be sieved.
     */

    public PrimeSieve(int n)
    {
        primes = new int[getPiHighBound(n)];
        // Note: This forces n>=1
        sieveRange = new IntegerRange(1, n);

        int numberOfPrimes = makePrimeList(n);
        primeRange = new IntegerRange(1, numberOfPrimes);
    }

    private static void sieveOfEratosthenes(final boolean[] composite)
    {
        int d1 = 8;
        int d2 = 8;
        int p1 = 3;
        int p2 = 7;
        int s1 = 7;
        int s2 = 3;
        int n = 0;
        int len = composite.length;
        boolean toggle = false;

        while (s1 < len) // -- scan sieve
        {
            if (!composite[n++]) // -- if a prime is found
            { // -- cancel its multiples
                int inc = p1 + p2;

                for (int k = s1; k < len; k += inc)
                {
                    composite[k] = true;
                }

                for (int k = s1 + s2; k < len; k += inc)
                {
                    composite[k] = true;
                }
            }

            if (toggle = !toggle) // Never mind, it's ok.
            {
                s1 += d2;
                d1 += 16;
                p1 += 2;
                p2 += 2;
                s2 = p2;
            }
            else
            {
                s1 += d1;
                d2 += 8;
                p1 += 2;
                p2 += 6;
                s2 = p1;
            }
        }
    }

    private int makePrimeList(int n)
    {
        boolean[] composite = new boolean[n / 3];

        sieveOfEratosthenes(composite);

        int[] prime = this.primes; // -- on stack for eff.
        boolean toggle = false;
        int p = 5, i = 0, j = 2;

        prime[0] = 2;
        prime[1] = 3;

        while (p <= n)
        {
            if (!composite[i++])
            {
                prime[j++] = p;
            }
            // -- never mind, it's ok.
            p += (toggle = !toggle) ? 2 : 4;
        }

        return j; // number of primes
    }

    /**
     * Get a high bound for pi(n), the number of primes less or equal n.
     *
     * @param n Bound of the primes.
     * @return A simple estimate of the number of primes <= n.
     */

    private static int getPiHighBound(long n)
    {
        if (n < 17)
        {
            return 6;
        }
        return (int) Math.floor(n / (Math.log(n) - 1.5));
    }

    public int getNthPrime(int n)
    {
        primeRange.containsOrFail(n);
        return primes[n - 1];
    }

    /**
     * Checks if a given number is prime.
     *
     * @param cand The number to be checked.
     * @return True if and only if the given number is prime.
     */

    public boolean isPrime(int cand)
    {
        // The candidate is interpreted as an one point interval!
        int primeMax = primeRange.getMax();
        int start = PrimeIteration.indexOf(primes, cand - 1, 0, primeMax);
        int end = primes[start] == cand ? start + 1 : start;
        return start < end;
    }

    public PrimeIteration getIteration()
    {
        return new PrimeIteration(this);
    }

    public PrimeIteration getIteration(int low, int high)
    {
        return new PrimeIteration(this, new IntegerRange(low, high));
    }

    /**
     * Gives the iteration of the prime numbers in the given range.
     *
     * @param range The range of the iteration.
     * @return The prime number iteration.
     */

    public PrimeIteration getIteration(IntegerRange range)
    {
        return new PrimeIteration(this, range);
    }

    /**
     * Gives the product of the prime numbers in the given interval.
     *
     * @param low  Lower bound of the iteration.
     * @param high Upper bound of the iteration.
     * @return The product of the prime numbers which are in the interval [low,
     * high].
     */

    public int getPrimorial(int low, int high)
    {
        return new PrimeIteration(this, new IntegerRange(low, high)).primorial();
    }

    /**
     * Gives the product of the prime numbers in the given range.
     *
     * @param range The range of the iteration.
     * @return The product of the prime numbers in the range.
     */

    public int getPrimorial(IntegerRange range)
    {
        return new PrimeIteration(this, range).primorial();
    }

    // ----------------------- inner class --------------------------

    /**
     * PrimeIteration.
     *
     * @author Peter Luschny
     * @version 2004-09-12
     */

    public static class PrimeIteration implements Iterator<Integer>, Iterable<Integer>
    {
        private final PrimeSieve sieve;
        private final IntegerRange sieveRange;
        private final IntegerRange primeRange;
        private final int start;
        private final int end;
        private int current;
        private AtomicInteger state;

        /**
         * Constructs the iteration for the passed sieve.
         *
         * @param sieve The sieve which is to be enumerated.
         */

        PrimeIteration(final PrimeSieve sieve)
        {
            this.sieve = sieve;
            current = start = 0;
            end = sieve.primeRange.getMax();
            sieveRange = sieve.sieveRange;
            primeRange = sieve.primeRange;
            state = new AtomicInteger(0);
        }

        /**
         * Constructs an iteration over a subrange of the range of the sieve.
         *
         * @param sieve      Prime number sieve to be used.
         * @param sieveRange Range of iteration.
         */

        PrimeIteration(final PrimeSieve sieve, final IntegerRange sieveRange)
        {
            this.sieve = sieve;
            this.sieveRange = sieveRange;

            sieve.sieveRange.containsOrFail(sieveRange);

            int sieveMax = sieve.primeRange.getMax();
            int primeMin = indexOf(sieve.primes, sieveRange.getMin() - 1, 0, sieveMax - 1);
            int primeMax = indexOf(sieve.primes, sieveRange.getMax(), primeMin, sieveMax);

            if (primeMin == primeMax) // there are no primes in this range
            {
                start = end = 0;
                primeRange = new IntegerRange(0, 0);
            }
            else
            {
                start = primeMin;
                end = primeMax;
                primeRange = new IntegerRange(primeMin + 1, primeMax);
            }

            current = primeMin;
            state = new AtomicInteger(0);
        }

        /**
         * Provides an iterator over the current prime number list. This
         * iterator is thread save.
         *
         * @return An iterator over the current prime number list.
         */

        public Iterator<Integer> iterator()
        {
            PrimeIteration result = this;

            if (0 != state.getAndIncrement())
            {
                result = new PrimeIteration(sieve, sieveRange);
            }

            result.current = result.start;

            return result;
        }

        /**
         * Returns The next prime number in the iteration.
         *
         * @return The next prime number in the iteration.
         */

        @Override
        public Integer next()
        {
            return sieve.primes[current++];
        }

        /**
         * Checks the current status of the finite iteration.
         *
         * @return True iff there are more prime numbers to be enumerated.
         */

        @Override
        public boolean hasNext()
        {
            return current < end;
        }

        /**
         * The (optional operation) to remove from the underlying collection the
         * last element returned by the iterator is not supported.
         *
         * @throws UnsupportedOperationException
         */

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        /**
         * Identifies the index of a prime number. Uses a (modified!) binary
         * search.
         *
         * @param data  List of prime numbers.
         * @param value The prime number given.
         * @param low   Lower bound for the index.
         * @param high  Upper bound for the index.
         * @return The index of the prime number.
         */

        static int indexOf(final int[] data, int value, int low, int high)
        {
            while (low < high)
            {
                // int mid = low + ((high - low) / 2);
                // Probably faster, and arguably as clear is:
                // int mid = (low + high) >>> 1;
                // In C and C++ (where you don't have
                // the >>> operator), you can do this:
                // mid = ((unsigned) (low + high)) >> 1;

                int mid = (low + high) >>> 1;

                if (data[mid] < value)
                {
                    low = mid + 1;
                }
                else
                {
                    high = mid;
                }
            }

            if (low >= data.length)
            {
                return low;
            }

            if (data[low] == value)
            {
                low++;
            }

            return low;
        }

        /**
         * Computes the number of primes in the iteration range.
         *
         * @return Cardinality of primes in iteration range.
         */

        public int getNumberOfPrimes()
        {
            if (0 == primeRange.getMax()) // If primeRange is empty...
            {
                return 0;
            }

            return primeRange.size();
        }

        /**
         * Computes the density of primes in the iteration.
         *
         * @return Ratio of the number of primes relative to the number of
         * integers in this iteration.
         */

        public double getPrimeDensity()
        {
            // Note: By construction sieveRange.size is always != 0.
            return (double) primeRange.size() / (double) sieveRange.size();
        }

        /**
         * Gives the interval [a,b] of the sieve.
         *
         * @return sieved interval.
         */

        public IntegerRange getSieveRange()
        {
            return IntegerRange.copyOf(sieveRange);
        }

        /**
         * Gives the range of the indices of the prime numbers in the
         * enumeration.
         *
         * @return Range of indices.
         */

        public IntegerRange getPrimeRange()
        {
            return IntegerRange.copyOf(primeRange);
        }

        /**
         * Gives the prime numbers in the iteration as an array.
         *
         * @return An array of prime numbers representing the iteration.
         */

        public int[] toArray()
        {
            int[][] v = new int[4][];
            int primeCard = primeRange.size();
            int[] primeList = new int[primeCard];

            System.arraycopy(sieve.primes, start, primeList, 0, primeCard);

            return primeList;
        }

        /**
         * Computes the product of all primes in the range of this iteration.
         */
        public int primorial()
        {
            if (0 == primeRange.getMax()) // if primeRange is empty...
            {
                return 1;
            }
            return IntStream
                    .range(0, primeRange.size())
                    .map(i -> sieve.primes[i])
                    .reduce(1, (i, j) -> i * j);
        }

    }
}
