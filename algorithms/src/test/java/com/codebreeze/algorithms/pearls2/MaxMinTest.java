package com.codebreeze.algorithms.pearls2;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.codebreeze.algorithms.pearls2.MaxMin.Algorithm.A;
import static com.codebreeze.algorithms.pearls2.MaxMin.Algorithm.B;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MaxMinTest {
    private final static int[] x = IntStream.range(0, 10_000)
        .map(i -> nextInt(0, 1_000_000))
        .toArray();

    @Test
    void apply_compare_algorithms() {
        // given
        // when
        MaxMin maxMinA = new MaxMin(A);
        MaxMin maxMinB = new MaxMin(B);
        MaxMin.MinMaxInt resultA = maxMinA.apply(x);
        MaxMin.MinMaxInt resultB = maxMinB.apply(x);

        System.out.println(resultA);
        System.out.println(resultB);

        // then
        assertThat(resultA.min()).isEqualTo(resultB.min());
        assertThat(resultA.max()).isEqualTo(resultB.max());
        assertThat(resultA.comparisonCount()).isGreaterThan(resultB.comparisonCount());
    }

    @Test
    void apply_failure_modes() {
        assertThatThrownBy(() -> new MaxMin(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new MaxMin(A).apply(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new MaxMin(A).apply(new int[0])).isInstanceOf(IllegalArgumentException.class);
    }
}
