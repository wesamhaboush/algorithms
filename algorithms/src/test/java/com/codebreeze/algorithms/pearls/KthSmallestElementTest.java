package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.codebreeze.algorithms.pearls.KthSmallestElement.Algorithm.LOOP;
import static com.codebreeze.algorithms.pearls.KthSmallestElement.Algorithm.RECURSION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KthSmallestElementTest {

    @Test
    void applyAsInt_regular_case() {
        // given
        // rank :    0,  1, 2, 3, 4, 5, 6
        // value : -20, -1, 1, 2, 4, 7, 8
        final int[] x = {1, 2, 7, -1, 8, -20, 4};

        // when
        KthSmallestElement recursion = new KthSmallestElement(RECURSION);
        KthSmallestElement loop = new KthSmallestElement(LOOP);

        // then
        int[] ks = {0, 1, 2, 3, 4, 5, 6};
        int[] x_ks = {-20, -1, 1, 2, 4, 7, 8};
        for (int i = 0; i < ks.length; i++) {
            assertThat(recursion.applyAsInt(specs(copy(x), ks[i]))).isEqualTo(x_ks[i]);
            assertThat(loop.applyAsInt(specs(copy(x), ks[i]))).isEqualTo(x_ks[i]);
        }
        assertThatThrownBy(() -> recursion.applyAsInt(specs(copy(x), 7))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> loop.applyAsInt(specs(copy(x), 7))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> recursion.applyAsInt(specs(copy(x), -1))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> loop.applyAsInt(specs(copy(x), -1))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void applyAsInt_empty_and_null() {
        // given
        final int[] x = {};

        // when
        KthSmallestElement recursion = new KthSmallestElement(RECURSION);
        KthSmallestElement loop = new KthSmallestElement(LOOP);

        // then
        assertThatThrownBy(() -> recursion.applyAsInt(specs(copy(x), 0))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> loop.applyAsInt(specs(copy(x), 0))).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> recursion.applyAsInt(specs(null, 0))).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> loop.applyAsInt(specs(null, 0))).isInstanceOf(NullPointerException.class);
    }

    @Test
    void applyAsInt_singleElement() {
        // given
        final int[] x = {4};

        // when
        KthSmallestElement recursion = new KthSmallestElement(RECURSION);
        KthSmallestElement loop = new KthSmallestElement(LOOP);

        // then
        assertThat(recursion.applyAsInt(specs(copy(x), 0))).isEqualTo(4);
        assertThat(loop.applyAsInt(specs(copy(x), 0))).isEqualTo(4);
    }

    private static int[] copy(int[] x) {
        return Arrays.copyOf(x, x.length);
    }

    private static KthSmallestElement.KthSmallestSpecs specs(int[] x, int k) {
        return new KthSmallestElement.KthSmallestSpecs(x, k);
    }
}
