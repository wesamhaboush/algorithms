package com.codebreeze.algorithms.bits;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BitsTest {

    @Test
    void isSet() {
        assertThat(Bits.isSet(2, 0)).isFalse();
        assertThat(Bits.isSet(2, 1)).isTrue();
        assertThat(Bits.isSet(2, 2)).isFalse();
        assertThat(Bits.isSet(2, 3)).isFalse();
    }
}
