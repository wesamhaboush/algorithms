package com.codebreeze.algorithms;

public class FactorialHyper
{
                private boolean nostart;
                private long S, K, A;

                public long factorial(int n)
        {
              if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

              nostart = false;
              int h = n / 2;
              S = h + 1;
              K = S + h;
              A = (n & 1) == 1 ? K : 1;
              if ((h & 1) == 1)
                  {
                      A = -A;
                  }
              K += 4;

              return hyperFact(h + 1) << h;
          }

                private long hyperFact(int l)
        {
              if (l > 1)
                  {
                      int m = l / 2;
                      return hyperFact(m) * hyperFact(l - m);
                  }

              if (nostart)
                  {
                      S -= K -= 4;
                      return S;
                  }

              nostart = true;
              return A;
          }
}
