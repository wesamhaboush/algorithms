package com.codebreeze.algorithms.tape.transpose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TapeTransposeTest {

    @Test
    void accept() {
        // given
        String[] input = {
            "A", "B", "C", "D",
            "E", "F", "G", "H",
            "I", "J", "K", "L",
            "M", "N", "O", "P"
        };

        // when
        TapeTranspose<String> tapeTranspose = new TapeTranspose<>();
        tapeTranspose.accept(input);

        // then
        assertThat(input).containsExactly(
            "A", "E", "I", "M",
            "B", "F", "J", "N",
            "C", "G", "K", "O",
            "D", "H", "L", "P"
        );
        assertThatThrownBy(() -> tapeTranspose.accept(new String[]{"1", "2"}))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("square matrices");
    }
}
