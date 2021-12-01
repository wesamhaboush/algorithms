package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RangeNearestSumToZeroTest {

    @Test
    void applyAsInt() {
        // given
        int[] x = {1, 2, -1, 3, -3, 7, -2, 17, -9};

        // when
        RangeNearestSumToZero rnstz = new RangeNearestSumToZero(x);

        // then
        assertThat(rnstz.applyAsInt(0)).isEqualTo(0);  // the sum will be zero for any range of size m = 0
        assertThat(rnstz.applyAsInt(1)).isEqualTo(1);  // 1
        assertThat(rnstz.applyAsInt(2)).isEqualTo(0);  // 3, -3
        assertThat(rnstz.applyAsInt(3)).isEqualTo(-1); // -1, 3, -3
        assertThat(rnstz.applyAsInt(4)).isEqualTo(1);  // 2, -1, 3, -3
        assertThat(rnstz.applyAsInt(5)).isEqualTo(2);  // 1, 2, -1, 3, -3
        assertThat(rnstz.applyAsInt(6)).isEqualTo(6);  // 2, -1, 3, -3, 7, -2
        assertThat(rnstz.applyAsInt(7)).isEqualTo(7);  // 1, 2, -1, 3, -3, 7, -2
        assertThat(rnstz.applyAsInt(8)).isEqualTo(14); // 2, -1, 3, -3, 7, -2, 17, -9
        assertThat(rnstz.applyAsInt(9)).isEqualTo(15); // 1, 2, -1, 3, -3, 7, -2, 17, -9
    }

    @Test
    void applyAsInt_failures() {
        // given
        int[] x1 = {};
        int[] x2 = {1, 2};

        // when
        RangeNearestSumToZero rnstz1 = new RangeNearestSumToZero(x1);
        RangeNearestSumToZero rnstz2 = new RangeNearestSumToZero(x1);

        // then
        assertThatThrownBy(() -> new RangeNearestSumToZero(null)).isInstanceOf(NullPointerException.class); // empty
        assertThatThrownBy(() -> rnstz1.applyAsInt(0)).isInstanceOf(NoSuchElementException.class); // empty
        assertThatThrownBy(() -> rnstz2.applyAsInt(-1)).isInstanceOf(IllegalArgumentException.class); // empty
        assertThatThrownBy(() -> rnstz2.applyAsInt(3)).isInstanceOf(IllegalArgumentException.class); // empty
    }
}
