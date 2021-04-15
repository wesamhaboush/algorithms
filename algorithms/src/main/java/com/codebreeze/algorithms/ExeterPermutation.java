package com.codebreeze.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExeterPermutation
{
    private static <T> List<List<T>> permute(final List<T> items,
                                             final int start)
    {
        List<List<T>> permutations = new ArrayList<>();
        if (start == items.size() - 1) {
            permutations.add(new ArrayList<>(items));
        }
        else {
            for (int i = start; i < items.size(); i++) {
                Collections.swap(items, i, start);
                permutations.addAll(permute(items, start + 1));
                Collections.swap(items, i, start);
            }
        }
        return permutations;
    }


    public static <T> List<List<T>> of(final List<T> items)
    {
        return permute(new ArrayList<>(items), 0); // create a mutable list
    }

}
