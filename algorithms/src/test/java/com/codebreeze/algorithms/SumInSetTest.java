package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SumInSetTest {

    @Test
    void regular_many_elements_cases() {
        // given
        // ordered
        // 1 3 5 6 7 9 10 20 30 31 101 1000
        int[] input = {3, 6, 30, 1000, 1, 101, 10, 31, 5, 20, 7, 9};

        // when
        SumInSet sumInSet = new SumInSet();

        // then
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), 4, 22)).isTrue();
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), 5, 22)).isTrue();
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), 5, 23)).isTrue();
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), 5, 30)).isTrue();
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), 5, 15)).isFalse();
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), 2, 1)).isFalse();
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), 0, 1)).isTrue();
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), input.length, 1000)).isFalse();
        assertThat(sumInSet.test(Arrays.copyOf(input, input.length), input.length, 1500)).isTrue();
    }

    @Test
    void empty_input_cases() {
        // given
        int[] empty = {};

        // when
        SumInSet sumInSet = new SumInSet();

        // then
        // the subset of size zero has a sum of zero (assumption, which is less than 15, so should this be true?
        // I think assuming empty sets exist with valid size zero, the sum on that set as an operation is defined
        // to be equal to zero, i think it should be good!
        assertThat(sumInSet.test(empty, 0, 15)).isTrue();
        assertThat(sumInSet.test(empty, 0, 0)).isTrue();
    }

    @Test
    void one_element_cases() {
        // given
        int[] one = {5};

        // when
        SumInSet sumInSet = new SumInSet();

        // then
        assertThat(sumInSet.test(one, 1, 4)).isFalse();
        assertThat(sumInSet.test(one, 1, 5)).isTrue();
        assertThat(sumInSet.test(one, 1, 6)).isTrue();
        assertThat(sumInSet.test(one, 1, 15)).isTrue();
        assertThat(sumInSet.test(one, 0, 15)).isTrue();
    }

    @Test
    void failure_cases() {
        // given
        // when
        SumInSet sumInSet = new SumInSet();

        // then
        assertThatThrownBy(() -> sumInSet.test(new int[]{2, 3}, 5, 1)).isInstanceOf(IllegalArgumentException.class);
    }
}
