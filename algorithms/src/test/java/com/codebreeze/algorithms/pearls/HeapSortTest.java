package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.pearls.HeapSort.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.copy;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.*;

class HeapSortTest {

    @Test
    void fail_on_null_algorithm() {
        for (var algorithm : Algorithm.values()) {
            assertThatCode(() -> new HeapSort(algorithm)).doesNotThrowAnyException();
        }
        assertThatThrownBy(() -> new HeapSort(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void works_for_empty_arrays() {
        // given
        int[] x = {};
        int[] expected = {};

        for (var algorithm : Algorithm.values()) {
            checkWorksForParameters(new HeapSort(algorithm), copy(x), expected);
        }
    }

    @Test
    void works_for_one_element_arrays() {
        // given
        int[] x = {1};
        int[] expected = {1};

        for (var algorithm : Algorithm.values()) {
            checkWorksForParameters(new HeapSort(algorithm), copy(x), expected);
        }
    }

    @Test
    void works_for_two_elements_array() {
        // given
        int[] xSorted = {1, 2};
        int[] xUnsorted = {2, 1};
        int[] expected = {1, 2};

        for (var algorithm : Algorithm.values()) {
            HeapSort sort = new HeapSort(algorithm);
            checkWorksForParameters(sort, copy(xSorted), expected);
            checkWorksForParameters(sort, copy(xUnsorted), expected);
        }
    }

    @Test
    void works_for_three_elements_array() {
        // given
        int[] xSorted = {1, 2, 7};
        int[] xUnsorted = {7, 2, 1};
        int[] expected = {1, 2, 7};

        for (var algorithm : Algorithm.values()) {
            HeapSort sort = new HeapSort(algorithm);
            checkWorksForParameters(sort, copy(xSorted), expected);
            checkWorksForParameters(sort, copy(xUnsorted), expected);
        }
    }

    @Test
    void works_for_many_elements_array() {
        // given
        int[] xUnsorted = IntStream.range(0, nextInt(4, 100))
            .map(i -> nextInt(0, 100))
            .toArray();
        int[] xSorted = copy(xUnsorted);
        Arrays.sort(xSorted);
        int[] expected = copy(xSorted);

        for (var algorithm : Algorithm.values()) {
            HeapSort sort = new HeapSort(algorithm);
            checkWorksForParameters(sort, copy(xSorted), expected);
            checkWorksForParameters(sort, copy(xUnsorted), expected);
        }
    }

    void checkWorksForParameters(HeapSort sort, int[] x, int[] expected) {
        // given
        // when
        sort.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }
}
