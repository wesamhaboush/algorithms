package com.codebreeze.algorithms;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

class PowerSet
{
    static <E> Set<Set<E>> of(final Set<E> set)
    {
        return Collections.unmodifiableSet(new InternalPowerSet<>(set));
    }

    private static final class InternalPowerSet<E>
            extends AbstractSet<Set<E>>
    {
        final Map<E, Integer> inputSet;

        InternalPowerSet(final Set<E> input)
        {
            if (input.size() > 30)
            {
                throw new IllegalArgumentException(
                        String.format("Too many elements to create power set: %s > 30", input.size()));
            }
            this.inputSet = indexMap(input);
        }

        @Override
        public int size()
        {
            return 1 << inputSet.size();
        }

        @Override
        public boolean isEmpty()
        {
            return false;
        }

        @Override
        public Iterator<Set<E>> iterator()
        {
            return new AbstractIndexedIterator<Set<E>>(size())
            {
                @Override
                protected Set<E> get(final int setBits)
                {
                    return new SubSet<>(inputSet, setBits);
                }
            };
        }

        @Override
        public boolean contains(Object obj)
        {
            if (obj instanceof Set)
            {
                Set<?> set = (Set<?>) obj;
                return inputSet.keySet()
                               .containsAll(set);
            }
            return false;
        }

        @Override
        public boolean equals(Object obj)
        {
            return super.equals(obj);
        }

        @Override
        public int hashCode()
        {
      /*
       * The sum of the sums of the hash codes in each subset is just the sum of
       * each input element's hash code times the number of sets that element
       * appears in. Each element appears in exactly half of the 2^n sets, so:
       */
            return inputSet.keySet()
                           .hashCode() << (inputSet.size() - 1);
        }
    }

    private abstract static class AbstractIndexedIterator<E>
            implements Iterator<E>
    {
        private final int size;
        private int position;

        protected abstract E get(int index);

        AbstractIndexedIterator(int size)
        {
            this(size, 0);
        }

        AbstractIndexedIterator(int size, int position)
        {
            this.size = size;
            this.position = position;
        }

        @Override
        public final boolean hasNext()
        {
            return position < size;
        }

        @Override
        public final E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            return get(position++);
        }
    }

    private static final class SubSet<E>
            extends AbstractSet<E>
    {
        private final Map<E, Integer> inputSet;
        private final int mask;

        SubSet(Map<E, Integer> inputSet, int mask)
        {
            this.inputSet = inputSet;
            this.mask = mask;
        }

        @Override
        public Iterator<E> iterator()
        {
            return new Iterator<E>()
            {
                final List<E> elements = inputSet.keySet()
                                                 .stream()
                                                 .collect(toList());
                int remainingSetBits = mask;

                @Override
                public boolean hasNext()
                {
                    return remainingSetBits != 0;
                }

                @Override
                public E next()
                {
                    int index = Integer.numberOfTrailingZeros(remainingSetBits);
                    if (index == 32)
                    {
                        throw new NoSuchElementException();
                    }
                    remainingSetBits &= ~(1 << index);
                    return elements.get(index);
                }
            };
        }

        @Override
        public int size()
        {
            return Integer.bitCount(mask);
        }

        @Override
        public boolean contains(Object o)
        {
            Integer index = inputSet.get(o);
            return index != null && (mask & (1 << index)) != 0;
        }
    }

    private static <E> Map<E, Integer> indexMap(Collection<E> list)
    {
        final Map<E, Integer> map = new HashMap<>(list.size());
        int i = 0;
        for (E e : list)
        {
            map.put(e, i++);
        }
        return Collections.unmodifiableMap(map);
    }

    /**
     * Is the set passed empty? Done
     * If not, take an element out
     * recursively call method on the remainder of the set
     * return the set composed of the Union of
     * (1) the powerset of the set without the element (from the recursive call)
     * (2) this same set (i.e., (1)) but with each element therein unioned with the element initially taken out
     */
    static <E> Set<Set<E>> of2(final Set<E> elements)
    {
        final Stream<Set<E>> combinationStream = elements.stream()
                                            .map(toElementAndSetWithoutItPair(elements)) //get <E, Set - { E }>
                                            .map(toElementAndSubPowerSetPair())
                                            .map(toPowerSetWithElementCombined())
                                            .flatMap(Collection::stream)
                                            .collect(toSet()).stream();
        final Stream<Set<E>> emptySetStream = Stream.of(Collections.emptySet());
        return Stream.concat(combinationStream, emptySetStream).collect(toSet());
    }

    private static <E> Function<Pair<E, Set<Set<E>>>, Set<Set<E>>> toPowerSetWithElementCombined()
    {
        return pair -> pair.getRight()
                           .stream()
                           .map(appendElementToPowerSubSet(pair))
                           .collect(toSet());
    }

    private static <E> Function<Set<E>, Set<E>> appendElementToPowerSubSet(final Pair<E, Set<Set<E>>> pair)
    {
        return set -> Stream.concat(set.stream(), Stream.of(pair.getLeft()))
                            .collect(toSet());
    }

    private static <E> Function<Pair<E, Set<E>>, Pair<E, Set<Set<E>>>> toElementAndSubPowerSetPair()
    {
        return pair -> Pair.of(pair.getLeft(), of2(pair.getRight()));

    }

    private static <E> Function<E, Pair<E, Set<E>>> toElementAndSetWithoutItPair(final Set<E> set)
    {
        return e -> Pair.of(e, set.stream()
                             .filter(i -> !Objects.equals(e, i))
                             .collect(toSet()));
    }

    private static class Pair<Left, Right>
    {
        private final Left left;
        private final Right right;

        private Pair(final Left left, final Right right)
        {
            this.left = left;
            this.right = right;
        }

        public static <Left, Right> Pair<Left, Right> of(final Left left, final Right right)
        {
            return new Pair<>(left, right);
        }

        public Left getLeft()
        {
            return left;
        }

        public Right getRight()
        {
            return right;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("Pair{");
            sb.append("left=")
              .append(left);
            sb.append(", right=")
              .append(right);
            sb.append('}');
            return sb.toString();
        }
    }
}
