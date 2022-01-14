package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BinarySearchUsingMultiplicationTest {

    @Test
    void applyAsInt_vanilla() {
        // given
        int[] elements = {1, 2, 3, 4, 5, 6, 7};

        // when
        BinarySearchUsingMultiplication bs = new BinarySearchUsingMultiplication(elements);

        // then
        assertThat(bs.applyAsInt(1)).isEqualTo(0);
        assertThat(bs.applyAsInt(2)).isEqualTo(1);
        assertThat(bs.applyAsInt(3)).isEqualTo(2);
        assertThat(bs.applyAsInt(4)).isEqualTo(3);
        assertThat(bs.applyAsInt(5)).isEqualTo(4);
        assertThat(bs.applyAsInt(6)).isEqualTo(5);
        assertThat(bs.applyAsInt(7)).isEqualTo(6);
        assertThatThrownBy(() -> bs.applyAsInt(99)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void applyAsInt_large() {
        // given
        int size = (int) Math.pow(2, 15) - 1;
        int[] elements = IntStream.range(0, size).map(i -> nextInt(0, 100000)).toArray();
        Arrays.sort(elements);

        // when
        BinarySearchUsingMultiplication bs = new BinarySearchUsingMultiplication(elements);

        // then
        for (int i = 0; i < elements.length; i++) {
            Set<Integer> indicesOfElement = indicesOf(elements, elements[i], i);
            assertThat(bs.applyAsInt(elements[i])).isIn(indicesOfElement);
        }

        assertThatThrownBy(() -> bs.applyAsInt(-5)).isInstanceOf(NoSuchElementException.class);
    }

    private Set<Integer> indicesOf(int[] elements, int element, int i) {
        Set<Integer> indices = new HashSet<>();
        indices.add(i);
        int previous = i - 1;
        while (previous >= 0 && elements[previous] == element) {
            indices.add(previous);
            previous--;
        }
        int next = i + 1;
        while (next < elements.length && elements[next] == element) {
            indices.add(next);
            next++;
        }
        return indices;
    }

    @Test
    void applyAsInt_empty_works() {
        // given
        int[] elements = {};

        // when
        BinarySearchUsingMultiplication bs = new BinarySearchUsingMultiplication(elements);

        // then
        assertThatThrownBy(() -> bs.applyAsInt(0)).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> bs.applyAsInt(99)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void applyAsInt_null_fails() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new BinarySearchUsingMultiplication(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void applyAsInt_wrong_size_fails() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new BinarySearchUsingMultiplication(new int[]{2, 7, 8, 10, 11}))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
