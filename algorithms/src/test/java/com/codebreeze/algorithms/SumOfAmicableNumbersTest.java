package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumOfAmicableNumbersTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(SumOfAmicableNumbers.calculate(300)).isEqualTo(504L);
        assertThat(SumOfAmicableNumbers.calculate(1000)).isEqualTo(504L);
        assertThat(SumOfAmicableNumbers.calculate(10000)).isEqualTo(31626L);
    }

}
