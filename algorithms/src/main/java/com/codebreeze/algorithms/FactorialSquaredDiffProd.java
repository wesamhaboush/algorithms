package com.codebreeze.algorithms;

public class FactorialSquaredDiffProd
{
                  public long factorial(int n)
          {
              if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

              if (n < 4)
                  {
                      return n < 2 ? 1 : n == 2 ? 2 : 6;
                  }

              long h = n / 2, q = h * h;
              long[] f = new long[(int) h];
              f[0] = (n & 1) == 1 ? 2 * q * n : 2 * q;
              int i = 1;

              for (int d = 1; d < n - 2; d += 2)
                  {
                      f[i++] = q -= d;
                  }

              return Math2.AsLong.product(f);
          }

}
