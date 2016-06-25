package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class MysteryPermutation
{
    private static <T> List<T> indicesToPermutation(final int[] v, final List<T> elements)
    {
        return Arrays
                .stream(v)
                .mapToObj(elements::get)
                .collect(toList());
    }


    private static void swap(final int[] v, final int i, final int j)
    {
        int t;
        t = v[i];
        v[i] = v[j];
        v[j] = t;
    }


    private static void rotateLeft(final int[] v, final int start)
    {
        final int n = v.length;
        int tmp = v[start];
        for (int i = start; i < n-1; i++)
        {
            v[i] = v[i+1];
        }
        v[n-1] = tmp;
    }


    private static <T> void permute(final int[] v,
                                    final int start,
                                    final List<T> elements,
                                    final List<List<T>> permutations
                                   )
    {
        final int n = v.length;
        permutations.add(indicesToPermutation(v, elements));
        if (start < n)
        {
            int i, j;
            for (i = n-2; i >= start; i--)
            {
                for (j = i + 1; j < n; j++)
                {
                    swap(v, i, j);
                    permute(v, i+1, elements, permutations);
                }
                rotateLeft(v, i);
            }
        }
    }

    public static <T> List<List<T>> permute(final List<T> elements)
    {
        final List<List<T>> permutations = new LinkedList<>();
        final int[] indices = IntStream.range(0, elements.size()).toArray();
        permute(indices, 0, elements, permutations);
        return permutations;
    }
}
