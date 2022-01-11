package com.codebreeze.algorithms.primitive.collections.iterable;

import com.codebreeze.algorithms.primitive.collections.iterator.IntIterator;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.IntConsumer;

public interface IntIterable {
    IntIterator iterator();

    default void forEach(IntConsumer action) {
        Objects.requireNonNull(action);

        IntIterator intIterator = iterator();

        while(intIterator.hasNext()) {
            action.accept(intIterator.next());
        }
    }

    default Spliterator.OfInt spliterator() {
        return new Spliterator.OfInt() {
            private final IntIterator intIterator = iterator();

            @Override
            public OfInt trySplit() {
                return null;
            }

            @Override
            public long estimateSize() {
                return Long.MAX_VALUE;
            }

            @Override
            public int characteristics() {
                return 0;
            }

            @Override
            public boolean tryAdvance(IntConsumer action) {
                if (action == null) throw new NullPointerException();
                if (intIterator.hasNext()) {
                    action.accept(intIterator.next());
                    return true;
                }
                return false;
            }
        };
    }
}
