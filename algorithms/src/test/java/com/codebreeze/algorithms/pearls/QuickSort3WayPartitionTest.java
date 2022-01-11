package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuickSort3WayPartitionTest {

    @Test
    void accept_fails_for_null() {
        assertThatThrownBy(() -> new QuickSort3WayPartition().accept(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void accept_empty() {
        // given
        int[] x = {};
        int[] expected = {};

        // when
        QuickSort3WayPartition ss = new QuickSort3WayPartition();
        ss.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }

    @Test
    void accept_single() {
        // given
        int[] x = {1};
        int[] expected = {1};

        // when
        QuickSort3WayPartition ss = new QuickSort3WayPartition();
        ss.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }

    @Test
    void accept_vanilla_case() {
        // given
        int[] x = {4, 9, 4, 4, 1, 9, 4, 4, 9, 4, 4, 1, 4};
        int[] expected = {1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 9, 9, 9};

        // when
        QuickSort3WayPartition qs3wp = new QuickSort3WayPartition();
        qs3wp.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }

    @Test
    void accept_all_same() {
        // given
        int[] x = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        int[] expected = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};

        // when
        QuickSort3WayPartition qs3wp = new QuickSort3WayPartition();
        qs3wp.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }
}
