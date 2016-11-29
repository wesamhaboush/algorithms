package com.codebreeze.algorithms;

public class FactorialPrimeBorwein
{
    private int[] primeList;
          private int[] multiList;

                  public int factorial(int n)
          {
              // For very small n the 'NaiveFactorial' is ok.
              if (n < 20)    { return NaiveFactorial.AsInt.of(n); }

              int lgN = 31 - Integer.numberOfLeadingZeros(n);
              int piN = 2 + (15 * n) / (8 * (lgN - 1));

              primeList = new int[piN];
              multiList = new int[piN];

              int len = primeFactors(n);
              return repeatedSquare(len, 1) << (n - Integer.bitCount(n));
          }

                  private int repeatedSquare(int len, int k)
          {
              if (len == 0)
                  {
                      return 1;
                  }

              int i = 0, mult = multiList[0];

              while (mult > 1)
                  {
                      if ((mult & 1) == 1) // is mult odd ?
                          {
                              primeList[len++] = primeList[i];
                          }

                      multiList[i++] = mult / 2;
                      mult = multiList[i];
                  }
              return (int)Power.apply(Math2.product(primeList, i, len - i), k) * (repeatedSquare(i, 2 * k));
          }

                  private int primeFactors(int n)
          {
              PrimeSieve sieve = new PrimeSieve(n);
              PrimeSieve.PrimeIteration pIter = sieve.getIteration(3, n);

              int maxBound = n / 2, count = 0;

              for (int prime : pIter)
                  {
                      int m = prime > maxBound ? 1 : 0;

                      if (prime <= maxBound)
                          {
                              int q = n;
                              while (q >= prime)
                                  {
                                      m += q /= prime;
                                  }
                          }

                      primeList[count] = prime;
                      multiList[count++] = m;
                  }
              return count;
          }
}
