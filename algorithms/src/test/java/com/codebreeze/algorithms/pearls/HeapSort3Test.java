package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.copy;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.*;

class HeapSort3Test {

    @Test
    void fail_on_null_algorithm() {
        assertThatCode(HeapSort3::new).doesNotThrowAnyException();
        assertThatThrownBy(() -> new HeapSort3().accept(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void works_for_empty_arrays() {
        // given
        int[] x = {};
        int[] expected = {};

        checkWorksForParameters(new HeapSort3(), copy(x), expected);
    }

    @Test
    void works_for_one_element_arrays() {
        // given
        int[] x = {1};
        int[] expected = {1};

        checkWorksForParameters(new HeapSort3(), copy(x), expected);
    }

    @Test
    void works_for_two_elements_array() {
        // given
        int[] xSorted = {1, 2};
        int[] xUnsorted = {2, 1};
        int[] expected = {1, 2};

        HeapSort3 sort = new HeapSort3();
        checkWorksForParameters(sort, copy(xSorted), expected);
        checkWorksForParameters(sort, copy(xUnsorted), expected);
    }

    @Test
    void works_for_three_elements_array() {
        // given
        int[] xSorted = {1, 2, 7};
        int[] xUnsorted = {7, 2, 1};
        int[] expected = {1, 2, 7};

        HeapSort3 sort = new HeapSort3();
        checkWorksForParameters(sort, copy(xSorted), expected);
        checkWorksForParameters(sort, copy(xUnsorted), expected);
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

        HeapSort3 sort = new HeapSort3();
        checkWorksForParameters(sort, copy(xSorted), expected);
        checkWorksForParameters(sort, copy(xUnsorted), expected);
    }

    void checkWorksForParameters(HeapSort3 sort, int[] x, int[] expected) {
        // given
        // when
        sort.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }
}
