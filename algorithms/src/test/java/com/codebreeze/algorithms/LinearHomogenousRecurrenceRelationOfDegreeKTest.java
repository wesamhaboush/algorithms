package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinearHomogenousRecurrenceRelationOfDegreeKTest {

    @Test
    void apply() {
        // given
//        vector<double> a{1, 5, 6, 7, 12, 3};
//        vector<double> c{2, 5, 1, 1, 11, 1, 4};
        int[] inputAs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] inputCs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // when
        LinearHomogenousRecurrenceRelationOfDegreeK linearRecurrence = new LinearHomogenousRecurrenceRelationOfDegreeK();

        // then
        assertThat(linearRecurrence.apply(inputCs.length, inputAs.length - 3, inputAs, inputCs)).containsExactly(0, 1, 2, 3, 4, 5, 6);
    }

    @Test
    void apply2() {
        // given
//        vector<double> a{1, 5, 6, 7, 12, 3};
//        vector<double> c{2, 5, 1, 1, 11, 1, 4};
        int[] as = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] cs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // when
        LinearHomogenousRecurrenceRelationOfDegreeK linearRecurrence = new LinearHomogenousRecurrenceRelationOfDegreeK();

        // then
//        assertThat(linearRecurrence.apply(cs.length, as.length + 2, as, cs)).containsExactly(0, 1, 2, 3, 4, 5, 6);
        assertThat(linearRecurrence.recurrence(cs.length - 1, as.length + 1, as, cs)).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 130);
        assertThat(linearRecurrence.recurrence(as, cs, cs.length - 1, as.length + 1)).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 130);
    }
}
