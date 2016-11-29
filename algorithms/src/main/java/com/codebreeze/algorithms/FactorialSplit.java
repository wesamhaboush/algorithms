package com.codebreeze.algorithms;

public class FactorialSplit
{


              private long N;

              public int factorial(int n)
              {
                  if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

                  if (n < 2)
                  {
                      return 1;
                  }

                  int p = 1;
                  int r = 1;
                  N = 1;

                  // log2n = floor(log2(n));
                  int log2n = 31 - Integer.numberOfLeadingZeros(n);
                  int h = 0, shift = 0, high = 1;

                  while (h != n)
                  {
                      shift += h;
                      h = n >>> log2n--;
                      int len = high;
                      high = (h & 1) == 1 ? h : h - 1;
                      len = (high - len) / 2;

                      if (len > 0)
                      {
                          p = p * product(len);
                          r = r * p;
                      }
                  }
                  return r << shift;
              }

              private int product(int n)
              {
                  int m = n / 2;
                  if (m == 0)
                  {
                      N += 2;
                      return (int)N;
                  }
                  if (n == 2)
                  {
                      return (int)((N += 2) * (N += 2));
                  }
                  return product(n - m) * product(m);
              }
          }

