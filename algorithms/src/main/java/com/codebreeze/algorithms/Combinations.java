package com.codebreeze.algorithms;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Combinations
{
    //really need a bag, but have to use a list cz java does not have a bag implementation
    //and i am lazy looking for one
    public static <T> Set<List<T>> of(final List<T> items)
    {
        return ofInternal(s -> true, items);
    }

    public static <T> Set<List<T>> of(final int size, final List<T> items)
    {
        return ofInternal(s -> s.size() == size, items);
    }

    public static <T> Set<List<T>> ofInternal(final Predicate<Set<Integer>> sizeFilter, final List<T> items)
    {
        final Set<Integer> indices = IntStream.iterate(0, i -> ++i)
                                              .limit(items.size())
                                              .mapToObj(i -> i)
                                              .collect(toSet());
        return PowerSet.of(indices)
                       .stream()
                       .filter(sizeFilter)
                       .map(toItems(items))
                       .collect(toSet());
    }

    private static <T> Function<Set<Integer>, List<T>> toItems(final List<T> items)
    {
        return indices -> indices.stream()
                                 .map(items::get)
                                 .collect(toList());
    }
}
