package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class PositiveTerminatingEquationTest {

    @Test
    void test1() {
        // given

        // when
        PositiveTerminatingEquation pte = new PositiveTerminatingEquation();

        // then
        for(int i : range(-10000, 1).toArray()) {
            assertThat(pte.test(i)).isFalse();
        }
        for(int i : range(1, 10000).toArray()) {
            assertThat(pte.test(i)).isTrue();
        }
    }
}
