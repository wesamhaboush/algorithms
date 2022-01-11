package com.codebreeze.algorithms.primitive.collections.heap;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleDoubleHeapTest {

    @Test
    void vanilla_case_min_heap() {
        // given
        double[] input = {11.0, 10.0, 2.5, 3.4, 1.4, 4.0};
        double[] expected = {1.4, 2.5, 3.4, 4.0, 10.0, 11.0};

        // when
        DoubleHeap dh = new SimpleDoubleHeap(false);
        for(double value : input) {
            dh.insert(value);
        }

        // then
        assertThat(dh.isEmpty()).isFalse();
        assertThat(dh.size()).isEqualTo(6);

        // when
        double[] result = IntStream.range(0, expected.length)
            .mapToDouble(i -> dh.extractM())
            .toArray();

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(dh.isEmpty()).isTrue();
        assertThat(dh.size()).isEqualTo(0);
    }

    @Test
    void vanilla_case_max_heap() {
        // given
        double[] input = {11.0, 10.0, 2.5, 3.4, 1.4, 4.0};
        double[] expected = {11.0, 10.0, 4.0, 3.4, 2.5, 1.4};

        // when
        DoubleHeap dh = new SimpleDoubleHeap(true);
        for(double value : input) {
            dh.insert(value);
        }

        // then
        assertThat(dh.isEmpty()).isFalse();
        assertThat(dh.size()).isEqualTo(6);

        // when
        double[] result = IntStream.range(0, expected.length)
            .mapToDouble(i -> dh.extractM())
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
        DoubleHeap dh = new SimpleDoubleHeap(false);

        // then
        assertThatThrownBy(dh::extractM).isInstanceOf(IllegalStateException.class);
    }
}
