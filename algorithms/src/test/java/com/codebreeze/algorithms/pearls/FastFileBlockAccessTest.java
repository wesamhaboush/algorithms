package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FastFileBlockAccessTest {

    @Test
    void apply_vanilla() {
        // given
        // when
        FastFileBlockAccess ffba = new FastFileBlockAccess();
        List<String> resultOdd = ffba.apply(5);
        List<String> resultEven = ffba.apply(6);
        List<String> resultOne = ffba.apply(1);
        List<String> resultZero = ffba.apply(0);

        // then
        assertThat(resultZero).containsExactly(
            "Start at 0"
        );
        assertThat(resultOne).containsExactly(
            "Start at 0",
            "Increment to 1"
        );
        assertThat(resultEven).containsExactly(
            "Start at 0",
            "Increment to 1",
            "Double to 2",
            "Increment to 3",
            "Double to 6"
        );
        assertThat(resultOdd).containsExactly(
            "Start at 0",
            "Increment to 1",
            "Double to 2",
            "Double to 4",
            "Increment to 5"
        );
    }

    @Test
    void apply_fails_for_negative() {
        // given
        // when
        FastFileBlockAccess ffba = new FastFileBlockAccess();

        // then
        assertThatThrownBy(() -> ffba.apply(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
