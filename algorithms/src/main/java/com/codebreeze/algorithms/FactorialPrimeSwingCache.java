package com.codebreeze.algorithms;

import java.util.HashMap;

public class FactorialPrimeSwingCache
{

              private PrimeSieve sieve;
              private int[] primeList;
              private HashMap<Integer, CachedPrimorial> cache;

              public FactorialPrimeSwingCache()
              {
              }

              public String getName()
              {
                  return "PrimeSwingCache   ";
              }

              public int factorial(int n)
              {
                  // For very small n the 'NaiveFactorial' is OK.
                  if (n < 20)    { return NaiveFactorial.AsInt.of(n); }

                  cache = new HashMap<Integer, CachedPrimorial>();
                  sieve = new PrimeSieve(n);
                  int piN = sieve.getIteration().getNumberOfPrimes();
                  primeList = new int[piN];

                  return recFactorial(n) << (n - Integer.bitCount(n));
              }

              private int recFactorial(int n)
              {
                  if (n < 2)
                  {
                      return 1;
                  }
                  final int v = recFactorial(n / 2);
                  return swing(n) * v * v;
              }

              private int swing(int n)
              {
                  if (n < 33)
                  {
                      return smallOddSwing[n];
                  }

                  int rootN = (int) Math.floor(Math.sqrt(n));
                  int count = 0, j = 1, low, high;

                  int prod = 1;

                  while (true)
                  {
                      high = n / j++;
                      low = n / j++;

                      if (low < rootN)
                      {
                          low = rootN;
                      }
                      if (high - low < 32)
                      {
                          break;
                      }

                      int primorial = getPrimorial(low + 1, high);

                      if (primorial != 1)
                      {
                          prod = prod * primorial;
                      }
                  }

                  PrimeSieve.PrimeIteration pIter = sieve.getIteration(3, high);

                  for (int prime : pIter)
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

                  return prod * Math2.product(primeList, 0, count - 1);
              }

              int getPrimorial(int low, int high)
              {
                  // System.out.print("Primorial [" + low + "," + high + "] " );
                  int primorial;
                  CachedPrimorial cachedPrimorial = cache.get(low);

                  if (null != cachedPrimorial)
                  {
                      int low1 = cachedPrimorial.high + 1;
                      int p = sieve.getPrimorial(low1, high);
                      primorial = p * cachedPrimorial.value;
                  }
                  else
                  {
                      primorial = sieve.getPrimorial(low, high);
                  }

                  cache.put(low, new CachedPrimorial(high, primorial));

                  return primorial;
              }

              private static int[] smallOddSwing =
                  { 1, 1, 1, 3, 3, 15, 5, 35, 35, 315, 63, 693, 231, 3003, 429, 6435,
                    6435, 109395, 12155, 230945, 46189, 969969, 88179, 2028117, 676039,
                    16900975, 1300075, 35102025, 5014575, 145422675, 9694845,
                    300540195, 300540195 };

    class CachedPrimorial
    {
        public int high;
        public int value;

        CachedPrimorial(int highBound, int val)
        {
            high = highBound;
            value = val;
        }
    }
          }


