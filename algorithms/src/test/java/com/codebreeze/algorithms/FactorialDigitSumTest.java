package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactorialDigitSumTest
{
    @Test
    public void calculate() throws Exception
    {
        //5 * 4 * 3 * 2 * 1 = 120 = 3
        assertThat(FactorialDigitSum.calculate(5)).isEqualTo(3);
        assertThat(FactorialDigitSum.calculate(100)).isEqualTo(648L);
    }

}
