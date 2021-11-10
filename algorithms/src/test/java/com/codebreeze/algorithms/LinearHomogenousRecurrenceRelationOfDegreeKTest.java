package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinearHomogenousRecurrenceRelationOfDegreeKTest {

    @Test
    void apply2() {
        // given
        int[] as = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] cs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // when
        LinearHomogenousRecurrenceRelationOfDegreeK linearRecurrence = new LinearHomogenousRecurrenceRelationOfDegreeK();

        // then
        assertThat(linearRecurrence.recurrence(cs.length - 1, as.length + 1, as, cs)).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 130);
        assertThat(linearRecurrence.recurrence(as, cs, cs.length - 1, as.length + 1)).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 130);
    }
}
