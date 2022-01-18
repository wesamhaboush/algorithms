package com.codebreeze.algorithms.pearls2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IsPrimeTest {

    @Test
    void test_vanilla() {
        // given
        // when
        IsPrime isPrime = new IsPrime();

        // then
        assertThat(isPrime.test(31)).isTrue();
        assertThat(isPrime.test(7919)).isTrue();
        assertThat(isPrime.test(7920)).isFalse();
    }
}
