package com.codebreeze.algorithms;

public class FactorialProductRecursive
{
                public int factorial(int n)
        {
              if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

              if (1 < n)
                  {
                      return recProduct(1, n);
                  }

              return 1;
          }

                private int recProduct(int n, int len)
        {
              if (1 < len)
                  {
                      int l = len / 2;
                      return recProduct(n, l) * (recProduct(n + l, len - l));
                  }

              return n;
          }
}
