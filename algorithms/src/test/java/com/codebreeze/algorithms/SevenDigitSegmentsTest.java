package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SevenDigitSegmentsTest {

    @Test
    void apply() {
        // given
        // when
        SevenDigitSegments sevenDigitSegments = new SevenDigitSegments();

        // then
        assertThat(sevenDigitSegments.apply(99999)).containsExactly(
            0b1011110,
            0b1011110,
            0b1011110,
            0b1011110,
            0b1011110
        );
//        for(byte b : sevenDigitSegments.apply(123)) {
//            System.out.println(Integer.toBinaryString(b));
//        }
        assertThat(sevenDigitSegments.apply(123)).containsExactly(
            0b1010111,
            0b110111,
            0b1010000,
            0b1111101,
            0b1111101
        );

    }
}
