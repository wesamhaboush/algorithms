package com.codebreeze.algorithms.primitive.collections.iterable;

import com.codebreeze.algorithms.primitive.collections.iterator.DoubleIterator;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.DoubleConsumer;

public interface DoubleIterable {
    DoubleIterator iterator();

    default void forEach(DoubleConsumer action) {
        Objects.requireNonNull(action);

        DoubleIterator iterator = iterator();

        while(iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    default Spliterator.OfDouble spliterator() {
        return new Spliterator.OfDouble() {
            private final DoubleIterator iterator = iterator();

            @Override
            public OfDouble trySplit() {
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
            public boolean tryAdvance(DoubleConsumer action) {
                if (action == null) throw new NullPointerException();
                if (iterator.hasNext()) {
                    action.accept(iterator.next());
                    return true;
                }
                return false;
            }
        };
    }
}
