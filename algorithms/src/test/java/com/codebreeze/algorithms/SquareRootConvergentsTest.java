package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareRootConvergentsTest {

    @Test
    public void applyAsInt_should_detect_all_cases() {
        // given
        SquareRootConvergents f = new SquareRootConvergents();

        // when
        // then
        assertThat(f.applyAsInt(0)).isEqualTo(0);
        assertThat(f.applyAsInt(1)).isEqualTo(0);
        assertThat(f.applyAsInt(2)).isEqualTo(0);
        assertThat(f.applyAsInt(3)).isEqualTo(0);
        assertThat(f.applyAsInt(4)).isEqualTo(0);
        assertThat(f.applyAsInt(5)).isEqualTo(0);
        assertThat(f.applyAsInt(6)).isEqualTo(0);
        assertThat(f.applyAsInt(7)).isEqualTo(0);
        assertThat(f.applyAsInt(8)).isEqualTo(1);
        assertThat(f.applyAsInt(9)).isEqualTo(1);
        assertThat(f.applyAsInt(10)).isEqualTo(1);
        assertThat(f.applyAsInt(11)).isEqualTo(1);
        assertThat(f.applyAsInt(12)).isEqualTo(1);
        assertThat(f.applyAsInt(13)).isEqualTo(2);
        assertThat(f.applyAsInt(50)).isEqualTo(7);
        assertThat(f.applyAsInt(100)).isEqualTo(15);
    }

    @Test
    // FIXME
    public void applyAsInt_should_detect_all_cases_delete() {
        // given
        SquareRootConvergents f = new SquareRootConvergents();

        // when
        // then
        assertThat(f.applyAsInt(100)).isEqualTo(15);
    }

    @Test
    public void applyAsInt_problem57() {
        // given
        SquareRootConvergents f = new SquareRootConvergents();

        // when
        // then
        assertThat(f.applyAsInt(1001)).isEqualTo(153);
        someMethod(new String[]{});
    }

    private static void someMethod(String[] stuff) {
        System.out.println(stuff.length);
    }
}
