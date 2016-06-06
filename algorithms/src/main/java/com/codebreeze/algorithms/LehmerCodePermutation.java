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
                .mapToObj(i ->  perm(items.size(), i)) // find the list of indices for this permutation
                .map(indices -> listOf(indices, items)) // get the permutation from the list of indices
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

    /*
    k is permutation number (find permutation number x)
    n is the number of items we are permutating
     */
    private static int[] perm(final int n, final int k)
    {
        int m = k;
        final int[] permuted = new int[n];
        final int[] elements = new int[n];
        // initialize the elements from 0 to n (assuming here that we are re-ordering 0 to n - 1 numbers
        for (int i = 0; i < n; i++)
        {
            elements[i] = i;
        }
        for (int i = 0; i < n; i++)
        {
            final int ind = m % (n - i);
            m = m / (n - i);
            permuted[i] = elements[ind];
            elements[ind] = elements[n - i - 1];
//            System.out.println(Arrays.toString(permuted));
//            System.out.println(Arrays.toString(elements));
        }
//        System.out.println("k = " + k + " n = " + n + " permutation = " +  Arrays.toString(permuted));
        return permuted;
    }

    public static int permutationToNumber(int[] perm)
    {
        int m = 1;
        final int n = perm.length;
        final int[] positions = new int[n];
        final int[] elements = new int[n];
        //initial positions and elements to be the same thing (base case)
        for (int i = 0; i < n; i++)
        {
            positions[i] = i;
            elements[i] = i;
        }
        int k = 0;
        for (int i = 0; i < n - 1; i++)
        {
            k += m * positions[perm[i]];
            m = m * (n - i);
            positions[elements[n - i - 1]] = positions[perm[i]];
            elements[positions[perm[i]]] = elements[n - i - 1];
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

    // perm[0]..perm[n-1] must contain the numbers in {0,..,n-1} in any order.
    public static int permToNumber(int[] perm, int start, int n) {
        // base case
        if (n == 1) return 0;

        // fix up perm[1]..perm[n-1] to be a permutation on {0,..,n-2}.
        for (int i = start+1; i < n; i++) {
            if (perm[i] > perm[start]) perm[i]--;
        }

        // recursively compute
        return perm[start] + n * permToNumber(perm, start + 1, n - 1);
    }

    // number must be >=0, < n!
    public static void numberToPerm(int number, int[] perm, int start, int n) {
        if (n == 1) {
            perm[start] = 0;
            return;
        }
        perm[start] = number % n;
        numberToPerm(number / n, perm, start + 1, n - 1);

        // fix up perm[1] .. perm[n-1]
        for (int i = start + 1; i < n; i++) {
            if (perm[i] >= perm[start]) perm[i]++;
        }
    }

    public static int[] numberToPerm(int number, int n) {
        final int[] perm = new int[n];
        numberToPerm(number, perm, 0, n);
        return perm;
    }
}
