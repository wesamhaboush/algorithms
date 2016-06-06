package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class JohnsonTrotterPermutation
{
    /*
    a directed integer is said to be mobile if it is greater than its immediate neighbor
    in the direction it is looking at.

    Note: -1 means the index is looking left (<), and 1 means the index is looking right (>)

    Initialize the first permutation with <1 <2 ... <n
while there exists a mobile integer
  find the largest mobile integer k
  swap k and the adjacent integer it is looking at
  reverse the direction of all integers larger than k
     */
    public static <T> List<List<T>> of(final List<T> elements)
    {
        final int N = elements.size();
        int[] p = new int[N];     // permutation
        int[] pi = new int[N];     // inverse permutation
        int[] dir = new int[N];     // direction = +1 or -1
        for (int i = 0; i < N; i++)
        {
            dir[i] = -1; // everything starts looking left
            p[i] = i;
            pi[i] = i;
        }
        final LinkedList<int[]> resultIndices = new LinkedList<>();
        perm(0, p, pi, dir, resultIndices);
        return IntStream
                .range(0, resultIndices.size())
                .mapToObj(resultIndices::get)
                .map(indices -> indicesToPermutation(elements, indices))
                .collect(toList());
    }

    private static <T> List<T> indicesToPermutation(final List<T> elements, final int[] indices)
    {
        return Arrays
                .stream(indices)
                .mapToObj(elements::get)
                .collect(toList());
    }

    public static void perm(int n, int[] p, int[] pi, int[] dir, List<int[]> resultIndices)
    {

        // base case - print out permutation
        if (n >= p.length)
        {
            resultIndices.add(IntStream
                    .range(0, p.length)
                    .map(i -> p[i])
                    .toArray());
        }
        else
        {
            perm(n + 1, p, pi, dir, resultIndices);
            for (int i = 0; i <= n - 1; i++)
            {
                // swap
                int z = p[pi[n] + dir[n]];
                p[pi[n]] = z;
                p[pi[n] + dir[n]] = n;
                pi[z] = pi[n];
                pi[n] = pi[n] + dir[n];

                perm(n + 1, p, pi, dir, resultIndices);
            }
            dir[n] = -dir[n];
        }
    }
}
