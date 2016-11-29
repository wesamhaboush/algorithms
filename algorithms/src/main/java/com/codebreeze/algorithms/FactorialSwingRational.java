package com.codebreeze.algorithms;

public class FactorialSwingRational
{
    private long D, N, h;
         private int i;

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
            return (recFactorial * recFactorial) * (swing(n));
          }

                private long swing(int n)
        {
              switch (n % 4)
                  {
                      case 1:
                              h = n / 2 + 1;
                              break;
                      case 2:
                              h = 2;
                              break;
                      case 3:
                              h = 2 * (n / 2 + 2);
                              break;
                      default:
                              h = 1;
                              break;
                      }

              N = 2 * (n + 2 - ((n + 1) & 1));
              D = 1;
              i = n / 4;

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
                      return new Rational(N -= 4, D++);
                  }

              return new Rational(h, 1);
          }

                // --------------------------------------------------------
                // A minimalistic rational arithmetic *only* for the use of
                // FactorialSwingRational. The sole purpose for inclusion

                // here is to make the description of the algorithm more
                // independent and more easy to port.
                // ---------------------------------------------------------
                private class Rational
        {

              private long num; // Numerator
              private long den; // Denominator

              public long getNumerator()
              {
                      long cd = Math2.gcd(num,den);
                      return num / cd;
                  }

              public Rational(long _num, long _den)
              {
                      long g = gcd(_num, _den);
                      num = _num / g;
                      den = _den / g;
                  }

              public Rational multiply(Rational r)
              {
                      return new Rational(num * r.num, den * r.den);
                  }

              private long gcd(long a, long b)
              {
                      long x, y;

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
                              long t = x % y;
                              x = y;
                              y = t;
                          }
                      return x;
                  }
          }
}
