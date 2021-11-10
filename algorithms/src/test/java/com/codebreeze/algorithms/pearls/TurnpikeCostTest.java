package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TurnpikeCostTest {

    @Test
    void applyAsInt_failures() {
        // given
        int[] costs = {3, 2, 5, 6};
        assertThatThrownBy(() -> new TurnpikeCost(costs).applyAsInt(3, 2))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new TurnpikeCost(costs).applyAsInt(3, 7))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new TurnpikeCost(costs).applyAsInt(-1, 2))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new TurnpikeCost(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void applyAsInt_others() {
        // given
        int[] costs = {3, 2, 5, 6};
        TurnpikeCost turnpikeCost = new TurnpikeCost(costs);

        // when/then
        assertThat(turnpikeCost.applyAsInt(0, 0)).isEqualTo(3);
        assertThat(turnpikeCost.applyAsInt(0, 1)).isEqualTo(5);
        assertThat(turnpikeCost.applyAsInt(0, 2)).isEqualTo(10);
        assertThat(turnpikeCost.applyAsInt(0, 3)).isEqualTo(16);
        assertThat(turnpikeCost.applyAsInt(1, 1)).isEqualTo(2);
        assertThat(turnpikeCost.applyAsInt(1, 2)).isEqualTo(7);
        assertThat(turnpikeCost.applyAsInt(1, 3)).isEqualTo(13);
        assertThat(turnpikeCost.applyAsInt(2, 2)).isEqualTo(5);
        assertThat(turnpikeCost.applyAsInt(2, 3)).isEqualTo(11);
        assertThat(turnpikeCost.applyAsInt(3, 3)).isEqualTo(6);
        assertThat(new TurnpikeCost(new int[]{46}).applyAsInt(0, 0)).isEqualTo(46);
    }
}
