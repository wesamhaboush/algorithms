package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AlexanderBogomolnyPermutation
{
    public static <T> List<T> createPermutation(final int[] sequence, final List<T> items)
    {
        final List<T> permutation = Arrays
                .stream(sequence)
                .mapToObj(i -> items.get(i - 1))
                .collect(Collectors.toList());
        return permutation;
    }

    private static <T> void visit(final Level level, final int[] sequence, final int k, final List<T> items, final List<List<T>> permutations)
    {
        level.value = level.value + 1;
        sequence[k] = level.value;
        if (level.value == items.size())
        {
            permutations.add(createPermutation(sequence, items));     // to the list box
        }
        else
        {
            for (int i = 0; i < items.size(); i++)
            {
                if (sequence[i] == 0)
                {
                    visit(level, sequence, i, items, permutations);
                }
            }
        }

        level.value = level.value - 1;
        sequence[k] = 0;
    }


    public static <T> List<List<T>> of(final List<T> items)
    {
        final Level level = new Level();
        level.value = -1;
        final int[] sequence = new int[items.size()];
        for (int i = 0; i < items.size(); i++)
        {
            sequence[i] = 0;
        }
        final List<List<T>> permutations = new LinkedList<>();
        visit(level, sequence, 0, items, permutations);
        return permutations;
    }

    private static class Level
    {
        int value;
    }
}
