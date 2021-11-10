package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubVectorClosestToZeroTest {

    @Test
    void empty() {
        // given
        int[] empty = {};

        // when
        SubVectorClosestToZero svcz = new SubVectorClosestToZero();

        // then
        assertThat(svcz.applyAsInt(empty)).isEqualTo(0);
    }

    @Test
    void applyAsInt_single() {
        // given
        int[] x1 = {7};
        int[] x2 = {-8};
        int[] x3 = {0};

        // when
        SubVectorClosestToZero svcz = new SubVectorClosestToZero();

        // then
        assertThat(svcz.applyAsInt(x1)).isEqualTo(7);
        assertThat(svcz.applyAsInt(x2)).isEqualTo(-8);
        assertThat(svcz.applyAsInt(x3)).isEqualTo(0);
    }

    @Test
    void applyAsInt_multiple() {
        // given
        int[] x1 = {-8, 1};
        int[] x2 = {-8, 3, -9, 2, -1, 8};
        int[] x3 = {15, -8, -9, -1, 6, -19, 80};
        int[] x4 = {19, 15, -8, -9, -10, 6, -19, 80};
        int[] x5 = {80, 15, -8, -9, -2, 6, -19, 19, 1, 1, 6};
        int[] x6 = {7, 8, -27};

        // when
        SubVectorClosestToZero svcz = new SubVectorClosestToZero();

        // then
        assertThat(svcz.applyAsInt(x1)).isEqualTo(1); // 1
        assertThat(svcz.applyAsInt(x2)).isEqualTo(0); // -9, 2, -1, 8 = 0
        assertThat(svcz.applyAsInt(x3)).isEqualTo(-1); // -1
        assertThat(svcz.applyAsInt(x4)).isEqualTo(-2); // 15, -8, -9
        assertThat(svcz.applyAsInt(x5)).isEqualTo(0); // -2, 6, -19, 19, 1, 1, 6
        assertThat(svcz.applyAsInt(x6)).isEqualTo(7); // 7
    }
}
