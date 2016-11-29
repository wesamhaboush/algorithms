package com.codebreeze.algorithms;

public class FactorialPrimeLeenstra
{
                  public int factorial(int n)
          {
              // For very small n the 'NaiveFactorial' is OK.
              if (n < 20)    { return NaiveFactorial.AsInt.of(n); }

              int rootN = (int) Math.floor(Math.sqrt(n));
              int log2N = 31 - Integer.numberOfLeadingZeros(n);
              int[] expBit = new int[log2N + 1];

              for (int j = 0; j < expBit.length; j++)
                  {
                      expBit[j] = 1;
                  }

              PrimeSieve sieve = new PrimeSieve(n);
              PrimeSieve.PrimeIteration pIter = sieve.getIteration(3, rootN);

              for (int prime : pIter)
                  {
                      int k = 0, m = 0, q = n;

                      do
                          {
                              m += q /= prime;

                          }
                      while (q >= 1);

                      while (m > 0)
                          {
                              if ((m & 1) == 1)
                                  {
                                      expBit[k] = expBit[k] * prime;
                                  }
                              m = m / 2;
                              k++;
                          }
                  }

              int j = 2, low = n, high;

              while (low != rootN)
                  {
                      high = low;
                      low = n / j++;

                      if (low < rootN)
                          {
                              low = rootN;
                          }

                      int primorial = sieve.getPrimorial(low + 1, high);

                      if (primorial != 1)
                          {
                              int k = 0, m = j - 2;

                              while (m > 0)
                                  {
                                      if ((m & 1) == 1)
                                          {
                                              expBit[k] = expBit[k]* primorial;
                                          }
                                      m = m / 2;
                                      k++;
                                  }
                          }
                  }

              int fact = expBit[log2N];
              for (int i = log2N - 1; i >= 0; --i)
                  {
                      fact = fact * fact * expBit[i];
                  }

              return fact << n - Integer.bitCount(n);
          }

}
