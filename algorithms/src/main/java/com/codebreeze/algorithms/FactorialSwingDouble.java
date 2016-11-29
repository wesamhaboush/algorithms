package com.codebreeze.algorithms;

public class FactorialSwingDouble
{
                public long factorial(int n)
        {
              if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

              return recFactorial(n);
          }

                private long recFactorial(int n)
        {
              if (n < 2)
                  {
                      return 1;
                  }
            final long recFactorial = recFactorial(n / 2);
            return recFactorial * recFactorial * swing(n);
          }

                private long swing(int n)
        {
              boolean oddN = (n & 1) == 1;
              boolean div = false;
              long h = n / 2;

              switch ((n / 2) % 4)
                  {
                      case 0:
                              h = oddN ? h + 1 : 1;
                              break;
                      case 1:
                              h = oddN ? 2 * (h + 2) : 2;
                              break;
                      case 2:
                              h = oddN ? 2 * (h + 1) * (h + 3) : 2 * (h + 1);
                              div = n > 7;
                              break;
                      case 3:
                              h = oddN ? 4 * (h + 2) * (h + 4) : 4 * (h + 2);
                              div = n > 7;
                              break;
                      }

              long b = h;

              long D = 1, N = oddN ? 2 * n : 2 * (n - 1);

              for (int i = n / 8; i > 0; --i)
                  {
                      long num = N * (N - 4), g = num;
                      long den = D * (D + 1), f = den;

                      N -= 8;
                      D += 2;

                      while (f != 0)
                          {
                              long t = g % f;
                              g = f;
                              f = t;
                          }

                      b = b * (num / g) / (den / g);
                  }

              if (div)
                  {
                      b = b / (n / 4);
                  }

              return b;
          }
}
