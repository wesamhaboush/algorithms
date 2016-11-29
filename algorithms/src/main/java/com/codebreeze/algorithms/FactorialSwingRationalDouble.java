package com.codebreeze.algorithms;

public class FactorialSwingRationalDouble
{
                private int D, N, g, h;
        private int i;

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

            final int i = recFactorial(n / 2);
            return i * i * swing(n);
          }

                private int swing(int n)
        {
              boolean oddN = (n & 1) == 1;
              boolean div = false;
              h = n / 2;

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

              g = div ? n / 4 : 1;
              N = 2 * (n + 3 + (n & 1));
              D = -1;
              i = n / 8;

              return product(i + 1).getNumerator();
          }

                private Rational product(int l)
        {
              if (l > 1)
                  {
                      int m = l / 2;
                      return product(m).multiply(product(l - m));
                  }

              if (i-- > 0)
                  {
                      N -= 8;
                      D += 2;
                      return new Rational(N * (N - 4), D * (D + 1));
                  }

              return new Rational(h, g);
          }

                // --------------------------------------------------------
                // A minimalistic rational arithmetic *only* for the use of
                // FactorialSwingRationalDouble. The sole purpose for inclusion

                // here is to make the description of the algorithm more
                // independent and more easy to port.
                // ---------------------------------------------------------
                private class Rational
        {

              private int num; // Numerator
              private int den; // Denominator

              public Rational(int _num, int _den)
              {
                      int c = gcd(_num, _den);
                      num = _num / c;
                      den = _den / c;
                  }


              public int getNumerator()
              {
                      int cd = gcd(num, den);
                      return num / cd;
                  }

              public Rational multiply(Rational r)
              {
                      return new Rational(num * r.num, den * r.den);
                  }

              private int gcd(int a, int b)
              {
                      int x, y;

                      if (a >= b)
                          {
                              x = a;
                              y = b;
                          }
                      else
                      {
                              x = b;
                              y = a;
                          }

                      while (y != 0)
                          {
                              int t = x % y;
                              x = y;
                              y = t;
                          }
                      return x;
                  }
          } // endOfRational
}
