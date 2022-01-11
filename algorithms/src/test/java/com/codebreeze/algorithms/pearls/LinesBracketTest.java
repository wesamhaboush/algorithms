package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.pair.DoubleDoublePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
y = 0.6 x - 1
y = 0.4 x + 2
y = 0.1 x + 3
y = 0.25 x + 4
 */
class LinesBracketTest {

    @Test
    void apply() {
        // given
        DoubleDoublePair[] lines = {
            DoubleDoublePair.of(0.6, -1),
            DoubleDoublePair.of(0.4, 2),
            DoubleDoublePair.of(0.1, 3),
            DoubleDoublePair.of(0.25, 4)
        };

        // when
        LinesBracket lb = new LinesBracket(lines);

        // then
        // points considered:
        // (1, 4), (0.5, 3), (1.5, 2)
        assertThat(lb.apply(DoubleDoublePair.of(1, 4)))
            .isEqualTo(
                Pair.of(
                    DoubleDoublePair.of(0.1, 3),
                    DoubleDoublePair.of(0.25, 4)
                )
            );
        assertThat(lb.apply(DoubleDoublePair.of(0.5, 3)))
            .isEqualTo(
                Pair.of(
                    DoubleDoublePair.of(0.4, 2),
                    DoubleDoublePair.of(0.1, 3)
                )
            );
        assertThat(lb.apply(DoubleDoublePair.of(1.5, 2)))
            .isEqualTo(
                Pair.of(
                    DoubleDoublePair.of(0.6, -1),
                    DoubleDoublePair.of(0.4, 2)
                )
            );
    }

    @Test
    void apply_failures() {
        // given
        DoubleDoublePair[] lines = {
            DoubleDoublePair.of(0.6, -1),
            DoubleDoublePair.of(0.4, 2),
            DoubleDoublePair.of(0.1, 3),
            DoubleDoublePair.of(0.25, 4)
        };

        // when
        LinesBracket lb = new LinesBracket(lines);

        // then
        // points considered:
        // (0.25, -1), (0.75, 4.25), (0, -1), (0, 2), (0, 3), (0, 4)
        assertThatThrownBy(() -> lb.apply(DoubleDoublePair.of(0.25, -1))) // below all lines
            .isInstanceOf(NoSuchElementException.class)
            .hasMessageContaining("not bracketed");
        assertThatThrownBy(() -> lb.apply(DoubleDoublePair.of(0.75, 4.25))) // below all lines
            .isInstanceOf(NoSuchElementException.class)
            .hasMessageContaining("not bracketed");
        assertThatThrownBy(() -> lb.apply(DoubleDoublePair.of(0, -1))) // below on a line
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("this point is on a line");
        assertThatThrownBy(() -> lb.apply(DoubleDoublePair.of(0, 2))) // below on a line
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("this point is on a line");
        assertThatThrownBy(() -> lb.apply(DoubleDoublePair.of(0, 3))) // below on a line
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("this point is on a line");
        assertThatThrownBy(() -> lb.apply(DoubleDoublePair.of(0, 4))) // below on a line
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("this point is on a line");
    }
}
