package com.codebreeze.algorithms;


import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.codebreeze.algorithms.BigIntegers.bi;
import static com.codebreeze.algorithms.BigIntegers.digitCount;
import static org.assertj.core.api.Assertions.assertThat;


public class BigIntegersTest {

    @Test
    public void digitCount_detects_correct_value_length() {
        IntStream.range(0, 10)
                .forEach(
                        n -> assertThat(digitCount(bi(n))).isEqualTo(1)
                );
        IntStream.range(10, 100)
                .forEach(
                        n -> assertThat(digitCount(bi(n))).isEqualTo(2)
                );
        IntStream.range(100, 1000)
                .forEach(
                        n -> assertThat(digitCount(bi(n))).isEqualTo(3)
                );
        IntStream.range(-999, -99)
                .forEach(
                        n -> assertThat(digitCount(bi(n))).isEqualTo(3)
                );
        IntStream.range(-99, -9)
                .forEach(
                        n -> assertThat(digitCount(bi(n))).isEqualTo(2)
                );
        IntStream.range(-9, 1)
                .forEach(
                        n -> assertThat(digitCount(bi(n))).isEqualTo(1)
                );
    }
}
