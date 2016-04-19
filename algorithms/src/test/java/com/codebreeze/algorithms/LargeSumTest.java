package com.codebreeze.algorithms;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LargeSumTest
{
    @Test
    public void calculate() throws Exception
    {
        final int[] expected = {5, 5, 3, 7, 3, 7, 6, 2, 3, 0};
//        assertThat(LargeSum.calculate2()).isEqualTo(expected);
        final int[] calculated = LargeSum.calculate(10);
        //        assertThat(LargeSum.calculate(10)).isEqualTo(expected);
        Arrays .stream(expected).forEach(System.out::print);
        Arrays .stream(calculated).forEach(System.out::print);
        assertThat(calculated).isEqualTo(expected);
    }
}
