package com.codebreeze.algorithms;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

/*
Pandigital products
Problem 32
We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example,
the 5-digit number, 15234, is 1 through 5 pandigital.

The third 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, second,
and third is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/second/third identity can be written as a 1 through 9 pandigital.

HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
public class PandigitalProductSum
{
     private static final Set<Pair<Integer, Integer>> VALID_SLICING_INDICES = Combinations.of(2, zeroToEight())
                                                                                         .stream()
                                                                                         .map(toPair())
                                                                                         .sorted(inFirstThenSecondOrder())
                                                                                         .collect(toCollection(() -> new TreeSet<>(inFirstThenSecondOrder())));

    private static Comparator<Pair<Integer, Integer>> inFirstThenSecondOrder()
    {
        return comparing((Function<Pair<Integer, Integer>, Integer>) Pair::getFirst)
                        .thenComparing(Pair::getSecond);
    }

    private static List<Integer> zeroToEight()
    {
        return IntStream.rangeClosed(0, 8)
                        .mapToObj(i -> i)
                        .collect(
                         toList());
    }

    private static Function<List<Integer>, Pair<Integer, Integer>> toPair()
    {
        return list -> Pair.of(list.get(0), list.get(1));
    }

    public static int calculate()
    {
        //1- first find all permutations
        final List<Integer> oneToNine = IntStream.iterate(1, n -> n + 1)
                                                 .limit(9)
                                                 .mapToObj(i -> i)
                                                 .collect(toList());
        final List<List<Integer>> permutations = HeapPermutations.of(oneToNine);

        //        System.out.println(permutations.size());
        //2- for each permutation
        //      a. find all breaking 2 points to break
        //      b. check first two sections' third is equal to the last section
        //      c. if yes, add combintion to result, otherwise skip

        return permutations.stream()
                           .flatMap(toTriples()) //find all combinations xx X yy = zz
                           .filter(isPandigital()) // allow pandigital tripes through
                           .map(Triple::getThird) // get the product
                           .distinct() // get distinct products only given the problem statement does not allow repetition (see hint)
                           .mapToInt(i -> i) // convert to primitive
                           .sum(); // and finally get the sum
    }

    private static Predicate<Triple<Integer, Integer, Integer>> isPandigital()
    {
        return triple -> triple.getFirst() * triple.getSecond() == triple.getThird();
    }

    private static Function<List<Integer>, Stream<Triple<Integer, Integer, Integer>>> toTriples()
    {
        return permutation -> VALID_SLICING_INDICES.stream()
                                                   .map(toTriple(permutation));
    }

    private static Function<Pair<Integer, Integer>, Triple<Integer, Integer, Integer>> toTriple(
            final List<Integer> permutation
                                                                                               )
    {
        //all ends and beginnings are inclusive
        return pair -> Triple.of(toNumber(permutation, 0, pair.getFirst()),
                                 toNumber(permutation, pair.getFirst(), pair.getSecond()),
                                 toNumber(permutation, pair.getSecond(), permutation.size()));
    }

    private static int toNumber(final List<Integer> permutation, final int start, final int end)
    {
        return FactoradicPermutation.digitsToNumber(permutation.subList(start, end)
                                                               .stream()
                                                               .mapToInt(i -> i)
                                                               .toArray());
    }

    private static class Triple<First, Second, Third>
    {
        private final First first;
        private final Second second;
        private final Third third;

        private Triple(final First first, final Second second, final Third third)
        {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public static <First, Second, Third> Triple<First, Second, Third> of(First first, Second second, Third third)
        {
            return new Triple<>(first, second, third);
        }

        public First getFirst()
        {
            return first;
        }

        public Second getSecond()
        {
            return second;
        }

        public Third getThird()
        {
            return third;
        }

        @Override
        public boolean equals(final Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            final Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
            return Objects.equals(first, triple.first) && Objects.equals(second, triple.second) && Objects.equals(third,
                                                                                                                  triple.third);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(first, second, third);
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("Triple{");
            sb.append("first=")
              .append(first);
            sb.append(", second=")
              .append(second);
            sb.append(", third=")
              .append(third);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class Pair<First, Second>
    {
        private final First first;
        private final Second second;

        private Pair(final First first, final Second second)
        {
            this.first = first;
            this.second = second;
        }

        public static <First, Second> Pair<First, Second> of(First first, Second second)
        {
            return new Pair<>(first, second);
        }

        public First getFirst()
        {
            return first;
        }

        public Second getSecond()
        {
            return second;
        }

        @Override
        public boolean equals(final Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            final Pair<?, ?> triple = (Pair<?, ?>) o;
            return Objects.equals(first, triple.first) && Objects.equals(second, triple.second);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(first, second);
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("Pair{");
            sb.append("first=")
              .append(first);
            sb.append(", second=")
              .append(second);
            sb.append('}');
            return sb.toString();
        }
    }
}
