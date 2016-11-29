package com.codebreeze.algorithms;

public class FactorialProductNaive
{
                public int factorial(int n)
        {
              if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

              int nFact = 1;

              for (int i = 2; i <= n; i++)
                  {
                      nFact = nFact * i;
                  }

              return nFact;
          }
}
