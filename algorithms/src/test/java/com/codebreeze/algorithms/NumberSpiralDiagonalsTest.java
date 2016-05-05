package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSpiralDiagonalsTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(NumberSpiralDiagonals.calculate(5)).isEqualTo(101L);
        assertThat(NumberSpiralDiagonals.calculate(1001)).isEqualTo(669171001L);
    }

}
