package com.codebreeze.algorithms;

public class NaiveFactorial
{


    public static class AsLong {
        public static long of(final long n)
        {
            if(n <= 1) return 1;
            return n * of(n - 1);
        }
    }

    public static class AsInt {
        public static int of(final int n)
        {
            if(n <= 1) return 1;
            return n * of(n - 1);
        }
    }
}
