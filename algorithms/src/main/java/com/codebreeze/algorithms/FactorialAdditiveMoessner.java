package com.codebreeze.algorithms;

public class FactorialAdditiveMoessner
{
    public static int factorial(int n)
    {
        int[] s = new int[n + 1];
        s[0] = 1;

        for (int m = 1; m <= n; m++)
        {
            s[m] = 0;
            for (int k = m; k >= 1; k--)
            {
                for (int i = 1; i <= k; i++)
                {
                    s[i] = s[i] + s[i - 1];
                }
            }
        }
        return s[n];
    }
}
