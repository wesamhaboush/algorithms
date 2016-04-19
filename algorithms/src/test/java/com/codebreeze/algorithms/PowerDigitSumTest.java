package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PowerDigitSumTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(PowerDigitSum.calculate(2, 1000)).isEqualTo(1366L);
        assertThat(PowerDigitSum.calculate(3, 1020)).isEqualTo(2151L);
    }

}
