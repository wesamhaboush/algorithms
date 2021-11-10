package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.codebreeze.algorithms.Utils.arrayOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VerificationTest {
    @Nested
    class AddVector {
        @Test
        void failure() {
            assertThatThrownBy(() -> new Verification.AddVectors().apply(arrayOf(1, 2), arrayOf(1)))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void success() {
            // given

            // when
            Verification.AddVectors addVectors = new Verification.AddVectors();

            // then
            assertThat(addVectors.apply(arrayOf(1, 2, 3), arrayOf(3, 4, 5)))
                .containsExactly(4, 6, 8);
            assertThat(addVectors.apply(arrayOf(1), arrayOf(3)))
                .containsExactly(4);
            assertThat(addVectors.apply(arrayOf(), arrayOf()))
                .isEmpty();
        }
    }

    @Nested
    class Max {
        @Test
        void failure() {
            assertThatThrownBy(() -> new Verification.Max().applyAsInt(arrayOf()))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void success() {
            // given

            // when
            Verification.Max max = new Verification.Max();

            // then
            assertThat(max.applyAsInt(arrayOf(1, 2, 3)))
                .isEqualTo(3);
            assertThat(max.applyAsInt(arrayOf(4)))
                .isEqualTo(4);
            assertThat(max.applyAsInt(arrayOf(4, 2)))
                .isEqualTo(4);
        }
    }

    @Nested
    class LinearSearch {

        @Test
        void success() {
            // given

            // when
            Verification.LinearSearch ls = new Verification.LinearSearch();

            // then
            assertThat(ls.apply(arrayOf(1, 2, 3), 3))
                .isEqualTo(2);
            assertThat(ls.apply(arrayOf(4), 3))
                .isEqualTo(-1);
            assertThat(ls.apply(arrayOf(4, 2), 4))
                .isEqualTo(0);
            assertThat(ls.apply(arrayOf(), 4))
                .isEqualTo(-1);
        }
    }

    @Nested
    class Exponential {
        @Test
        void failure() {
            assertThatThrownBy(() -> new Verification.Exponential().apply(1, -1))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void success() {
            // given

            // when
            Verification.Exponential e = new Verification.Exponential();

            // then
            assertThat(e.apply(4, 0))
                .isEqualTo(1);
            assertThat(e.apply(2, 11))
                .isEqualTo(2048);
            assertThat(e.apply(2, 10))
                .isEqualTo(1024);
        }
    }
}
