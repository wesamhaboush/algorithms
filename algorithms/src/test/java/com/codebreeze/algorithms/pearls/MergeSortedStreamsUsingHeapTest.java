package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.iterable.ArrayIntIterable;
import com.codebreeze.algorithms.primitive.collections.iterable.IntIterable;
import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MergeSortedStreamsUsingHeapTest {

    @Test
    void apply_vanilla() {
        // given
        Set<IntIterable> iterables = Set.of(
            new ArrayIntIterable(1, 3, 4),
            new ArrayIntIterable(2, 5, 8),
            new ArrayIntIterable(5, 7, 9)
        );
        IntIterable expected = new ArrayIntIterable(1, 2, 3, 4, 5, 5, 7, 8, 9);

        // when
        IntIterable result = new MergeSortedStreamsUsingHeap().apply(iterables);

        // then
        assertThat(ArrayIntList.of(result)).isEqualTo(ArrayIntList.of(expected));
    }

    @Test
    void apply_vanilla_different_size_iterator() {
        // given
        Set<IntIterable> iterables = Set.of(
            new ArrayIntIterable(1, 3, 4, 17),
            new ArrayIntIterable(2, 5, 7, 8, 9, 12, 33),
            new ArrayIntIterable(5)
        );
        IntIterable expected = new ArrayIntIterable(1, 2, 3, 4, 5, 5, 7, 8, 9, 12, 17, 33);

        // when
        IntIterable result = new MergeSortedStreamsUsingHeap().apply(iterables);

        // then
        assertThat(ArrayIntList.of(result)).isEqualTo(ArrayIntList.of(expected));
    }

    @Test
    void apply_copes_with_empty_ones() {
        // given
        Set<IntIterable> iterables = Set.of(
            new ArrayIntIterable(1, 3, 4),
            new ArrayIntIterable(),
            new ArrayIntIterable(5, 7, 9)
        );
        IntIterable expected = new ArrayIntIterable(1, 3, 4, 5, 7, 9);

        // when
        IntIterable result = new MergeSortedStreamsUsingHeap().apply(iterables);

        // then
        assertThat(ArrayIntList.of(result)).isEqualTo(ArrayIntList.of(expected));
    }

    @Test
    void apply_copes_with_all_empty_ones() {
        // given
        Set<IntIterable> iterables = Set.of(
            new ArrayIntIterable(),
            new ArrayIntIterable(),
            new ArrayIntIterable()
        );
        IntIterable expected = new ArrayIntIterable();

        // when
        IntIterable result = new MergeSortedStreamsUsingHeap().apply(iterables);

        // then
        assertThat(ArrayIntList.of(result)).isEqualTo(ArrayIntList.of(expected));
    }

    @Test
    void apply_fails_for_null_set() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new MergeSortedStreamsUsingHeap().apply(null))
            .isInstanceOf(NullPointerException.class);
    }

}
