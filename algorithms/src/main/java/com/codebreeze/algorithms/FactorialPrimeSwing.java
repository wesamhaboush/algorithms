package com.codebreeze.algorithms;

public class FactorialPrimeSwing
{
    private static int[] smallOddSwing = {1, 1, 1, 3, 3, 15, 5, 35, 35, 315, 63, 693, 231, 3003, 429, 6435, 6435,
                                          109395, 12155, 230945, 46189, 969969, 88179, 2028117, 676039, 16900975,
                                          1300075, 35102025, 5014575, 145422675, 9694845, 300540195, 300540195};

    public static int factorial(int n)
    {
        // For very small n the 'NaiveFactorial' is OK.
        if (n < 20)
        {
            return NaiveFactorial.AsInt.of(n);
        }

        final PrimeSieve primeSieve = new PrimeSieve(n);
        int pLen = primeSieve
                .getIteration()
                .getNumberOfPrimes();
        final int[] primeList = new int[pLen];

        return recFactorial(n, primeList, primeSieve) << (n - Integer.bitCount(n));
    }

    private static int recFactorial(int n, final int[] primeList, final PrimeSieve primeSieve)
    {
        if (n < 2)
        {
            return 1;
        }
        return Power.AsInt.apply(recFactorial(n / 2, primeList, primeSieve), 2) * swing(n, primeList, primeSieve);
    }


    private static int swing(int n, final int[] primeList, final PrimeSieve primeSieve)
    {
        if (n < 33)
        {
            return smallOddSwing[n];
        }

        int sqrtN = (int) Math.floor(Math.sqrt(n));
        PrimeSieve.PrimeIteration pIter0 = primeSieve.getIteration(3, sqrtN);
        PrimeSieve.PrimeIteration pIter1 = primeSieve.getIteration(sqrtN + 1, n / 3);

        int count = 0;

        for (int prime : pIter0)
        {
            int q = n, p = 1;

            while ((q /= prime) > 0)
            {
                if ((q & 1) == 1)
                {
                    p *= prime;
                }
            }

            if (p > 1)
            {
                primeList[count++] = p;
            }
        }

        for (int prime : pIter1)
        {
            if (((n / prime) & 1) == 1)
            {
                primeList[count++] = prime;
            }
        }

        int prod = primeSieve.getPrimorial(n / 2 + 1, n);
        return prod * Math2.product(primeList);
    }

}
