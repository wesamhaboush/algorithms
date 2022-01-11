package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.copy;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RadixSortStringsTest {

    @Test
    void accept_fails_for_null() {
        assertThatThrownBy(() -> new RadixSortStrings().accept(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void accept_empty() {
        // given
        String[] x = {};
        String[] expected = {};

        // when
        RadixSortStrings rss = new RadixSortStrings();
        rss.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }

    @Test
    void accept_single_element() {
        // given
        String[] x = {"1"};
        String[] expected = {"1"};

        // when
        RadixSortStrings rss = new RadixSortStrings();
        rss.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }

    @Test
    void accept_two_element() {
        // given
        String[] xSorted = {"1", "2"};
        String[] xUnsorted = {"2", "1"};
        String[] expected = {"1", "2"};

        // when
        RadixSortStrings rss = new RadixSortStrings();
        rss.accept(xSorted);
        rss.accept(xUnsorted);

        // then
        assertThat(xSorted).isEqualTo(expected);
        assertThat(xUnsorted).isEqualTo(expected);
    }

    @Test
    void accept_vanilla_case() {
        // given
        String[] x = IntStream.range(0, nextInt(3, 100))
            .mapToObj(i -> randomAlphanumeric(0, 15))
            .toArray(String[]::new);
        String[] expected = copy(x);
        Arrays.sort(expected);

        // when
        RadixSortStrings rss = new RadixSortStrings();
        rss.accept(x);

        // then
        assertThat(x).isEqualTo(expected);
    }
}
