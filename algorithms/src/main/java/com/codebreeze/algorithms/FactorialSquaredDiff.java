package com.codebreeze.algorithms;

public class FactorialSquaredDiff
{
                public long factorial(long n)
        {
              if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

              if (n < 2)
                  {
                      return 1;
                  }

              long h = n / 2, q = h * h;
              long r = (n & 1) == 1 ? 2 * q * n : 2 * q;
              long f = r;

              for (int d = 1; d < n - 2; d += 2)
                  {
                      f = f * (q -= d);
                  }

              return f;
          }
}
