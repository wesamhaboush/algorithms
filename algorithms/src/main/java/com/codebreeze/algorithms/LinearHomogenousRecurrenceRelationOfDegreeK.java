package com.codebreeze.algorithms;

import java.util.Arrays;

/*
A Kth-order linear recurrence with constant coefficients defines a series as

a_n = c_1 a_n-1 + c_2 a_n-2 + ... + c_k a_n-k + c_k+1

a_n = c_1 a_n-1 + c_2 a_n-2 + ... + c_k a_n-k

for k = 1, n = 1

a1 = c1 a0
a2 = c1 a1 + c2 a0
a3 = c1 a2 + c2 a1 + c3 a0

where c1, ..., c_k+1 are real numbers. Write a program that with input k, a_1, ..., a_k, c1, ..., c_k+1, and N produces
the output a1 through a_n. How difficult is that program compared to a program that solves one particular fifth-order
 recurrence, but does so without using arrays?
 */
public class LinearHomogenousRecurrenceRelationOfDegreeK {
    public int[] apply(int k, int n, int[] as, int[] cs) {
        return recurrence(as, cs, k, n);
    }

    public int nthLinearRecurrence(int[] a, int[] c) {
        if (a.length != c.length - 1) {
            throw new IllegalArgumentException("a.length must be equal to c.length - 1 for the algorithm to work");
        }

        int result = 0;
        for (int i = 0; i < a.length; ++i) {
            result += c[i] * a[a.length - 1 - i];
        }
        return result + c[c.length - 1];
    }

    public int[] recurrence(int K, int N, int[] a, int[] c) {
        System.out.println("as: " + Arrays.toString(a));
        System.out.println("cs: " + Arrays.toString(c));
        System.out.println("c_" + K + " = " + c[K]);
        int[] newA = new int[N];
        System.arraycopy(a, 0, newA, 0, a.length);
        for (int n = K; n < N; ++n) {
            newA[n] = c[K];
            for (int i = 0; i < K; ++i) {
                System.out.println("a_" + n + " += a_" + (n - i - 1) + " * c_" + i + " = " + newA[n - i - 1] + " * " + c[i] + " = " + newA[n - i - 1] * c[i]);
                newA[n] += newA[n - i - 1] * c[i];
            }
        }
        System.out.println("newA: " + Arrays.toString(newA));
        return newA;
    }


    public int[] /*size N*/ recurrence(int[] as /*size K*/, int[] /* size K+1 */ cs, int K, int N) {
        int[] output = new int[N];
        System.arraycopy(as, 0, output, 0, K);
        for (int n = K; n < N; ++n) {
            // Calculate the new value
            int r = cs[K];
            for (int i = 0; i < K; ++i) {
                r += as[K - i - 1] * cs[i];
            }
            // Propagate the values backward
            if (K - 1 >= 0) {
                System.arraycopy(as, 1, as, 0, K - 1);
            }
            as[K - 1] = r;
            output[n] = r;
        }
        return output;
    }
}
