package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackWhiteBeansTest {

    @Test
    void apply() {
        // given/when
        BlackWhiteBeans bwb = new BlackWhiteBeans();

        // then
        assertThat(bwb.apply(2, 2)).isLessThan(1000);
        assertThat(bwb.apply(2, 21)).isLessThan(1000);
        assertThat(bwb.apply(21, 2)).isLessThan(1000);
        assertThat(bwb.apply(21, 21)).isLessThan(1000);
        assertThat(bwb.apply(21, 50)).isLessThan(1000);
    }
}
