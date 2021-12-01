package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static com.codebreeze.algorithms.pearls.CountOnesInBinary.Algorithm.Bentley;
import static com.codebreeze.algorithms.pearls.CountOnesInBinary.Algorithm.Boer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CountOnesInBinaryTest {

    @Test
    void apply() {
        // given

        // when
        CountOnesInBinary coibBen = new CountOnesInBinary(Bentley);
        CountOnesInBinary coibBoe = new CountOnesInBinary(Boer);

        // then
        // 0 1 2 3 4 5 6 7
        // 0 1 1 2 1 2 2 3
        assertThat(coibBen.apply(8)).containsExactly(0, 1, 1, 2, 1, 2, 2, 3);
        assertThat(coibBoe.apply(8)).containsExactly(0, 1, 1, 2, 1, 2, 2, 3);
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
        // 0 1 1 2 1 2 2 3 1 2 2  3  2  3  3  4
        assertThat(coibBen.apply(16)).containsExactly(0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4);
        assertThat(coibBoe.apply(16)).containsExactly(0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4);
    }

    @Test
    void apply_failures() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new CountOnesInBinary(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new CountOnesInBinary(Boer).apply(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
