package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FirstOccurrenceBinarySearchTest {

    @Test
    void applyAsInt() {
        // given
        Integer[] input = {7, 9, 18, 80};

        // when
        FirstOccurrenceBinarySearch<Integer> firstOccurrenceBinarySearch = new FirstOccurrenceBinarySearch<>();

        // then
        assertThatThrownBy(() -> firstOccurrenceBinarySearch.applyAsInt(input, 15))
            .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> firstOccurrenceBinarySearch.applyAsInt(input, 3))
            .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() ->firstOccurrenceBinarySearch.applyAsInt(input, 100))
            .isInstanceOf(NoSuchElementException.class);
        assertThat(firstOccurrenceBinarySearch.applyAsInt(input, 7)).isEqualTo(0);
        assertThat(firstOccurrenceBinarySearch.applyAsInt(input, 9)).isEqualTo(1);
        assertThat(firstOccurrenceBinarySearch.applyAsInt(input, 18)).isEqualTo(2);
        assertThat(firstOccurrenceBinarySearch.applyAsInt(input, 80)).isEqualTo(3);
    }

    @Test
    void applyAsInt_repeat() {
        // given
        Integer[] input = {7, 7, 9, 9, 9, 18, 18, 80};

        // when
        FirstOccurrenceBinarySearch<Integer> firstOccurrenceBinarySearch = new FirstOccurrenceBinarySearch<>();

        // then
        assertThatThrownBy(() -> firstOccurrenceBinarySearch.applyAsInt(input, 15))
            .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> firstOccurrenceBinarySearch.applyAsInt(input, 3))
            .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() ->firstOccurrenceBinarySearch.applyAsInt(input, 100))
            .isInstanceOf(NoSuchElementException.class);
        assertThat(firstOccurrenceBinarySearch.applyAsInt(input, 7)).isEqualTo(0);
        assertThat(firstOccurrenceBinarySearch.applyAsInt(input, 9)).isEqualTo(2);
        assertThat(firstOccurrenceBinarySearch.applyAsInt(input, 18)).isEqualTo(5);
        assertThat(firstOccurrenceBinarySearch.applyAsInt(input, 80)).isEqualTo(7);
    }
}
