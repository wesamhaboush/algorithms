package com.codebreeze.algorithms.rotate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReverseRotate3SectionsBrokenArrayTest {

    @Test
    void accept() {
        // given
        Integer[] input = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // when
        // assume we are reversing ABC to CBA, with 0 to 9 indices, and 3 4 5 skipped (i.e. left the same)
        // A = 0 1 2
        // B = 3 4 5
        // C = 6 7 8 9
        // so final correct result should be: 6789 345 012
        new ReverseRotate3SectionsBrokenArray<Integer>().accept(input, new ReverseRotate3SectionsBrokenArray.RotationSpecs(3, 5));

        // then
        assertThat(input).containsExactly(6, 7, 8, 3, 4, 5, 9, 0, 1, 2);
    }
}
