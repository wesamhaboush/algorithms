package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import java.util.function.IntBinaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractGcdTest {
    @Test
    void can_find_gcd_for_various_combinations() {
        assertThat(operator().applyAsInt(2, 0)).isEqualTo(2);
        assertThat(operator().applyAsInt(0, 2)).isEqualTo(2);
        assertThat(operator().applyAsInt(0, 0)).isEqualTo(0);
        assertThat(operator().applyAsInt(270, 192)).isEqualTo(6);
    }

    abstract IntBinaryOperator operator();
}
