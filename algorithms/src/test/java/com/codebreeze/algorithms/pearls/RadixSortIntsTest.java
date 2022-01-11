package com.codebreeze.algorithms.pearls;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.copy;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RadixSortIntsTest {

    @Test
    void accept_fails_for_null() {
        assertThatThrownBy(() -> new RadixSortInts().accept(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void accept_empty() {
        // given
        int[] x = {};
        int[] expected = {};

        // when
        RadixSortInts rsi = new RadixSortInts();
        rsi.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }

    @Test
    void accept_single() {
        // given
        int[] x = {1};
        int[] expected = {1};

        // when
        RadixSortInts rsi = new RadixSortInts();
        rsi.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }

    @Test
    void accept_two() {
        // given
        int[] xSorted = {1, 2};
        int[] xUnsorted = {2, 1};
        int[] expected = {1, 2};

        // when
        RadixSortInts rsi = new RadixSortInts();
        rsi.accept(xSorted);
        rsi.accept(xUnsorted);

        // then
        assertThat(xSorted).isEqualTo(expected);
        assertThat(xUnsorted).isEqualTo(expected);
    }

    @Test
    void accept_vanilla_case() {
        // given
        int[] x = IntStream.range(0, nextInt(3, 100))
            .map(i -> RandomUtils.nextInt(0, 100))
            .toArray();
        int[] expected = copy(x);
        Arrays.sort(expected);

        // when
        RadixSortInts rsi = new RadixSortInts();
        rsi.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }
}
