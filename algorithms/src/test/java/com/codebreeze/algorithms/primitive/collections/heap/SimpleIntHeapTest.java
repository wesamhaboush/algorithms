package com.codebreeze.algorithms.primitive.collections.heap;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleIntHeapTest {

    @Test
    void vanilla_case_min_heap() {
        // given
        int[] input = {11, 10, 2, 3, 1, 4};
        int[] expected = {1, 2, 3, 4, 10, 11};

        // when
        IntHeap dh = new SimpleIntHeap(false);
        for(int value : input) {
            dh.insert(value);
        }

        // then
        assertThat(dh.isEmpty()).isFalse();
        assertThat(dh.size()).isEqualTo(6);

        // when
        int[] result = IntStream.range(0, expected.length)
            .map(i -> dh.extractM())
            .toArray();

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(dh.isEmpty()).isTrue();
        assertThat(dh.size()).isEqualTo(0);
    }

    @Test
    void vanilla_case_max_heap() {
        // given
        int[] input = {11, 10, 2, 3, 1, 4};
        int[] expected = {11, 10, 4, 3, 2, 1};

        // when
        IntHeap dh = new SimpleIntHeap(true);
        for(int value : input) {
            dh.insert(value);
        }

        // then
        assertThat(dh.isEmpty()).isFalse();
        assertThat(dh.size()).isEqualTo(6);

        // when
        int[] result = IntStream.range(0, expected.length)
            .map(i -> dh.extractM())
            .toArray();

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(dh.isEmpty()).isTrue();
        assertThat(dh.size()).isEqualTo(0);
    }

    @Test
    void empty() {
        // given
        // when
        IntHeap dh = new SimpleIntHeap(false);

        // then
        assertThatThrownBy(dh::extractM).isInstanceOf(IllegalStateException.class);
    }
}
