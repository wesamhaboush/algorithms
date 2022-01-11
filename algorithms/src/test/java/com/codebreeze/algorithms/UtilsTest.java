package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UtilsTest {

    @Test
    void retry_fails_forever() {
        // given
        Runnable runnable = () -> { throw new IllegalStateException("i will always fail"); };
        // when
        // then
        assertThatThrownBy(() -> Utils.retry(10, Set.of(IllegalStateException.class), runnable))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void retry_succeeds_after_few_attempts() {
        // given
        Runnable runnable = new Runnable() {
            private int i = 0;
            @Override
            public void run() {
                i++;
                if(i != 5) {
                    throw new IllegalStateException("succeeds at fifth try!, but this is try number: " + i);
                }
            }
        };

        // when
        // then
        assertThatCode(() -> Utils.retry(10, Set.of(IllegalStateException.class), runnable))
            .doesNotThrowAnyException();
    }

    @Test
    void retry_succeeds_after_few_attempts_multiple() {
        // given
        Runnable runnable = new Runnable() {
            private int i = 0;
            @Override
            public void run() {
                i++;
                if(i < 5) {
                    throw new IllegalStateException("succeeds at fifth try!, but this is try number: " + i);
                }
                if(i < 8) {
                    throw new IllegalArgumentException("succeeds at fifth try!, but this is try number: " + i);
                }
            }
        };

        // when
        // then
        assertThatCode(() -> Utils.retry(10, Set.of(IllegalStateException.class, IllegalArgumentException.class), runnable))
            .doesNotThrowAnyException();
    }

    @Test
    void retry_illegal_inputs() {
        // given
        Runnable runnable = () -> {};

        // when
        // then
        assertThatThrownBy(() -> Utils.retry(-1, Set.of(IllegalStateException.class), runnable))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Utils.retry(0, Set.of(IllegalStateException.class), runnable))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Utils.retry(10, null, runnable))
            .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Utils.retry(10, Set.of(), runnable))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Utils.retry(10, Set.of(IllegalStateException.class), null))
            .isInstanceOf(NullPointerException.class);
    }
}
