package com.codebreeze.algorithms;

public class FactorialPrimeSchoenhage
  {
              private int[] primeList;
              private int[] multiList;

              public FactorialPrimeSchoenhage()
              {
              }

              public String getName()
              {
                  return "PrimeSchoenhage   ";
              }

              public int factorial(int n)
              {
                  // For very small n the 'NaiveFactorial' is ok.
                  if (n < 20)    { return NaiveFactorial.AsInt.of(n); }

                  int log2n = 31 - Integer.numberOfLeadingZeros(n);
                  int piN = 2 + (15 * n) / (8 * (log2n - 1));

                  primeList = new int[piN];
                  multiList = new int[piN];

                  int len = primeFactors(n);
                  return nestedSquare(len) << (n - Integer.bitCount(n));
              }

              private int nestedSquare(int len)
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
                  final int nestedSquare = nestedSquare(i);
                  if (len <= i)
                  {
                      return nestedSquare * nestedSquare;
                  }

                  return Math2.product(primeList, i, len - i) * (nestedSquare * nestedSquare);
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
