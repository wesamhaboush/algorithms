package com.codebreeze.algorithms;

public class FactorialBoitenSplit
{
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

              // log2n = floor(log2(n));
              int log2n = 31 - Integer.numberOfLeadingZeros(n);
              int h = 0, shift = 0, k = 1;

              while (h != n)
                  {
                      shift += h;
                      h = n >>> log2n--;
                      int high = (h & 1) == 1 ? h : h - 1;

                      while (k != high)
                          {
                              k += 2;
                              p = p * k;
                          }
                      r = r * p;
                  }
              return r << shift;
          }
}
