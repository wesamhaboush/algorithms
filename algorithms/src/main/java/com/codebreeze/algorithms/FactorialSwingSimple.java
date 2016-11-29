package com.codebreeze.algorithms;

public class FactorialSwingSimple
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
              return recFactorial* recFactorial*(swing(n));
          }

                private int swing(int n)
        {
              int z;

              switch (n % 4)
                  {
                      case 1:
                              z = n / 2 + 1;
                              break;
                      case 2:
                              z = 2;
                              break;
                      case 3:
                              z = 2 * (n / 2 + 2);
                              break;
                      default:
                              z = 1;
                              break;
                      }

              int b = z;
              z = 2 * (n - ((n + 1) & 1));

              for (int i = 1; i <= n / 4; i++, z -= 4)
                  {
                      b = b * (z)/(i);
                  }

              return b;
          }
}
