package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/*
http://antoinecomeau.blogspot.ca/2014/07/mapping-between-permutations-and.html
https://stackoverflow.com/questions/1506078/fast-permutation-number-permutation-mapping-algorithms

 */
public class LehmerCodePermutation {
    public static <T> List<List<T>> of(List<T> items)
    {
        return IntStream.range(0, factorialInt(items.size()))
                .mapToObj(i ->  perm(items.size(), i))
                .map(indices -> listOf(indices, items))
                .collect(toList());
    }

    private static <T> List<T> listOf(final int[] indices,
                                      final List<T> items)
    {
        return Arrays
                .stream(indices)
                .mapToObj(items::get)
                .collect(toList());
    }

    private static int[] perm(int n, int k)
    {
        int i;
        int ind;
        int m = k;
        int[] permuted = new int[n];
        int[] elems = new int[n];
        for (i = 0; i < n; i++)
        {
            elems[i] = i;
        }
        for (i = 0; i < n; i++)
        {
            ind = m % (n - i);
            m = m / (n - i);
            permuted[i] = elems[ind];
            elems[ind] = elems[n - i - 1];
        }
//        System.out.println("k = " + k + " n = " + n + " permutation = " +  Arrays.toString(permuted));
        return permuted;
    }

    private static int inv(int[] perm)
    {
        int i, k = 0, m = 1;
        int n = perm.length;
        int[] pos = new int[n];
        int[] elems = new int[n];
        for (i = 0; i < n; i++)
        {
            pos[i] = i;
            elems[i] = i;
        }
        for (i = 0; i < n - 1; i++)
        {
            k += m * pos[perm[i]];
            m = m * (n - i);
            pos[elems[n - i - 1]] = pos[perm[i]];
            elems[pos[perm[i]]] = elems[n - i - 1];
        }
        return k;
    }

    private static long factorial(long n)
    {
        if (n == 1)
        {
            return 1;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }

    private static int factorialInt(int n)
    {
        return (int)factorial(n);
    }
}
