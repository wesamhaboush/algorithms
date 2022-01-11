package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static com.codebreeze.algorithms.Utils.copy;
import static org.assertj.core.api.Assertions.*;

class SortVaryingLengthBitStringsTest {

    @Test
    void accept_failure() {
        assertThatThrownBy(() -> new SortVaryingLengthBitStrings().accept(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void accept_empty() {
        // given
        String[] x = {};

        // when
        SortVaryingLengthBitStrings svlbs = new SortVaryingLengthBitStrings();

        // then
        assertThatCode(() -> svlbs.accept(x)).doesNotThrowAnyException();
    }

    @Test
    void accept_single() {
        // given
        String[] x = {"0"};
        String[] expected = copy(x);
        // when
        SortVaryingLengthBitStrings svlbs = new SortVaryingLengthBitStrings();
        svlbs.accept(x);

        // then
        assertThat(x).isEqualTo(expected); // nothing removed or added.
    }

    @Test
    void accept_same() {
        // given
        String[] x = {"010", "010", "010", "010", "010"};
        String[] expected = copy(x);
        // when
        SortVaryingLengthBitStrings svlbs = new SortVaryingLengthBitStrings();
        svlbs.accept(x);

        // then
        assertThat(x).isEqualTo(expected); // nothing removed or added.
    }

    @Test
    void accept_vanilla_same_length_all_unique() {
        // given
        String[] x = {"1010", "0110", "0101", "0010", "0100"};
        String[] expected = {"0010", "0100", "0101", "0110", "1010"};

        // when
        SortVaryingLengthBitStrings svlbs = new SortVaryingLengthBitStrings();
        svlbs.accept(x);

        // then
        assertThat(x).isEqualTo(expected); // nothing removed or added.
    }

    @Test
    void accept_vanilla_same_length_non_unique() {
        // given
        String[] x = {"1010", "0110", "0111", "1010", "0110"};
        String[] expected = {"0110", "0110", "0111", "1010", "1010"};

        // when
        SortVaryingLengthBitStrings svlbs = new SortVaryingLengthBitStrings();
        svlbs.accept(x);

        // then
        assertThat(x).isEqualTo(expected); // nothing removed or added.
    }

    @Test
    void accept_vanilla_mixed_length_non_unique() {
        // given
        String[] x = {"10100", "10", "111", "111", "1010", "11", "0"};
        // if we pad shorter strings
//        String[] expected = {"0", "10", "11", "111", "111", "1010", "10100"};
        // if we do not pad, here is the output
        String[] expected = {"0", "10", "10100", "1010", "11", "111", "111"};

        // when
        SortVaryingLengthBitStrings svlbs = new SortVaryingLengthBitStrings();
        svlbs.accept(x);

        // then
        assertThat(x).isEqualTo(expected); // nothing removed or added.
    }
}
