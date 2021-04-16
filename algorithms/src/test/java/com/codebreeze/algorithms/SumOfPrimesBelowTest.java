package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumOfPrimesBelowTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(SumOfPrimesBelow.calculate(10)).isEqualTo(17);
        assertThat(SumOfPrimesBelow.calculate(2000000)).isEqualTo(142913828922L);
    }

}
