package com.codebreeze.algorithms;

public class FactorialSwing
{

                  private int ndiv4OddFact, ndiv2OddFact;

                  public int factorial(int n)
          {
              if (n < 0)
                  {
                      throw new ArithmeticException("Factorial: n has to be >= 0, but was " + n);
                  }

              ndiv4OddFact = ndiv2OddFact = 1;

              return oddFactorial(n) << (n - Integer.bitCount(n));
          }

                  private int oddFactorial(int n)
          {
              int oddFact;
              if (n < 17)
                  {
                      oddFact = smallOddFactorial[n];
                  }
              else
              {
                  final int oddFactorial = oddFactorial(n / 2);
                  oddFact = oddFactorial * oddFactorial * oddSwing(n);
              }

              ndiv4OddFact = ndiv2OddFact;
              ndiv2OddFact = oddFact;
              return oddFact;
          }

                  private int oddSwing(int n)
          {
              if (n < 33)
                  {
                      return smallOddSwing[n];
                  }

              int len = (n - 1) / 4;
              if ((n % 4) != 2)
                  {
                      len++;
                  }
              int high = n - ((n + 1) & 1);

              int ndiv4 = n / 4;
              int oddFact = ndiv4 < 17 ? smallOddFactorial[ndiv4] : ndiv4OddFact;

              return product(high, len) / (oddFact);
          }

                  private static int product(int m, int len)
          {
              if (len == 1)
                  {
                      return m;
                  }
              if (len == 2)
                  {
                      return m * (m - 2);
                  }

              int hlen = len >>> 1;
              return product(m - hlen * 2, len - hlen) * product(m, hlen);
          }

                  private static int[] smallOddSwing =
                  { 1, 1, 1, 3, 3, 15, 5, 35, 35, 315, 63, 693, 231, 3003, 429, 6435, 6435, 109395, 12155, 230945, 46189, 969969,
                          88179, 2028117, 676039, 16900975, 1300075, 35102025, 5014575, 145422675, 9694845, 300540195, 300540195 };

                  private static int[] smallOddFactorial =
                  { 1, 1, 1, 3, 3, 15, 45, 315, 315, 2835, 14175, 155925, 467775, 6081075, 42567525, 638512875, 638512875 };

}
