package com.codebreeze.algorithms;

public class FactorialPrimeVardi
{
    private PrimeSieve sieve;

    public int factorial(int n)
    {
        // For very small n the 'NaiveFactorial' is ok.
        if (n < 20)
        {
            return NaiveFactorial.AsInt.of(n);
        }

        sieve = new PrimeSieve(n);

        return recFactorial(n);
    }

    private int recFactorial(int n)
    {
        if (n < 2)
        {
            return 1;
        }

        if ((n & 1) == 1)
        {
            return recFactorial(n - 1) * n;
        }

        final int fa = recFactorial(n / 2);
        return middleBinomial(n) * fa * fa;
    }

    private int middleBinomial(int n) // assuming n = 2k
    {
        if (n < 50)
        {
            return (int)binom[n / 2];
        }

        int k = n / 2;
        int pc = 0;
        int pp = 0;
        int e;
        int rootN = (int) Math.floor(Math.sqrt(n));

        int bigPrimes = sieve.getPrimorial(k + 1, n);
        int smallPrimes = sieve.getPrimorial(k / 2 + 1, n / 3);

        PrimeSieve.PrimeIteration pIter = sieve.getIteration(rootN + 1, n / 5);
        int[] primeList = new int[pIter.getNumberOfPrimes()];

        for (int prime : pIter)
        {
            if ((n / prime & 1) == 1) // if n/prime is odd...
            {
                primeList[pc++] = prime;
            }
        }
        int prodPrimes = Math2.product(primeList, 0, pc - 1);

        pIter = sieve.getIteration(1, rootN);
        int[] primePowerList = new int[pIter.getNumberOfPrimes()];

        for (int prime : pIter)
        {
            if ((e = expSum(prime, n)) > 0)
            {
                primePowerList[pp++] = Power.AsInt.apply(prime, e);
            }
        }

        int powerPrimes = Math2.product(primePowerList, 0, pp - 1);

        return bigPrimes
                * smallPrimes
                * prodPrimes
                * powerPrimes;
    }

    private int expSum(int p, int n)
    {
        int exp = 0, q = n / p;

        while (0 < q)
        {
            exp += q & 1;
            q /= p;
        }

        return exp;
    }

    private static long[] binom = {1, 2, 6, 20, 70, 252, 924, 3432, 12870, 48620, 184756, 705432, 2704156, 10400600,
                                   40116600, 155117520, 601080390, 2333606220L, 9075135300L, 35345263800L,
                                   137846528820L, 538257874440L, 2104098963720L, 8233430727600L, 32247603683100L};
}
