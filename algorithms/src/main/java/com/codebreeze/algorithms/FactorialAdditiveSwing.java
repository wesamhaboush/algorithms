package com.codebreeze.algorithms;

public class FactorialAdditiveSwing
{
                public int factorial(int n)
        {
              if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

              return recFactorial(n);
          }

                private int recFactorial(int n)
        {
              if (n < 2)
                  {
                      return 1;
                  }

            final int recFactorial = recFactorial(n / 2);
            return (recFactorial * recFactorial) * swing(n);
          }

                public int swing(int n)
        {
              int w = 1;

              if (n > 1)
                  {
                      n = n + 2;
                      int[] s = new int[n + 1];

                      s[0] = s[1] = 0;
                      s[2] = w;

                      for (int m = 3; m <= n; m++)
                          {
                              s[m] = s[m - 2];
                              for (int k = m; k >= 2; k--)
                                  {
                                      s[k] = s[k] + (s[k - 2]);
                                      if ((k & 1) == 1) // if k is odd
                                          {
                                              s[k] = s[k] + (s[k - 1]);
                                          }
                                  }
                          }
                      w = s[n];
                  }
              return w;
          }
}
