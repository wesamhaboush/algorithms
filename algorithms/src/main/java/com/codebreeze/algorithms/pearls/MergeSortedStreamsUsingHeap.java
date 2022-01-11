package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.iterable.IntIterable;
import com.codebreeze.algorithms.primitive.collections.iterator.IntIterator;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Function;

import static java.util.Comparator.comparingInt;
import static java.util.Objects.requireNonNull;

public class MergeSortedStreamsUsingHeap implements Function<Set<IntIterable>, IntIterable> {
    @Override
    public IntIterable apply(Set<IntIterable> intIterables) {
        return new MergedIterable(requireNonNull(intIterables));
    }

    private record MergedIterable(Set<IntIterable> intIterables) implements IntIterable {

        @Override
        public IntIterator iterator() {
            return new IntIterator() {
                private final PriorityQueue<IntIteratorValue> heap = new PriorityQueue<>(
                    comparingInt(IntIteratorValue::value)
                ) {{
                    for (IntIterable intIterable : intIterables) {
                        IntIterator iterator = intIterable.iterator();
                        if (iterator.hasNext()) {
                            offer(new IntIteratorValue(iterator, iterator.next()));
                        }
                    }
                }};

                @Override
                public boolean hasNext() {
                    return !heap.isEmpty();
                }

                @Override
                public int next() {
                    IntIteratorValue element = requireNonNull(heap.poll());
                    if (element.intIterator().hasNext()) {
                        heap.offer(new IntIteratorValue(element.intIterator(), element.intIterator().next()));
                    }
                    return element.value;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException(
                        "cannot remove from external file iterator. This iterator is a merger only"
                    );
                }
            };
        }
    }

    private static record IntIteratorValue(IntIterator intIterator, int value) {
    }
}
