package com.codebreeze.algorithms;

import java.math.BigInteger;

public class Power
{
    public static final BigInteger TWO = BigInteger.valueOf(2);

    public static BigInteger apply(BigInteger a, BigInteger b)
    {
        if (b == BigInteger.ZERO)
        {
            return BigInteger.ONE;
        }
        if (b == BigInteger.ONE)
        {
            return a;
        }
        if (isEven(b))
        {
            return apply(a.multiply(a), b.divide(TWO)); //even a=(a^2)^b/2
        }
        else
        {
            return a.multiply(apply(a.multiply(a), b.divide(TWO))); //odd  a=a*(a^2)^b/2
        }
    }

    private static boolean isEven(long b)
    {
        return b % 2 == 0;
    }

    private static boolean isEven(BigInteger b)
    {
        return b.remainder(TWO) == BigInteger.ZERO;
    }

    public static long apply(long a, long b)
    {
        if ( b == 0)        return 1;
        if ( b == 1)        return a;
        if (isEven( b ))    return     apply ( a * a, b/2); //even a=(a^2)^b/2
        else                return a * apply ( a * a, b/2); //odd  a=a*(a^2)^b/2
    }

}
