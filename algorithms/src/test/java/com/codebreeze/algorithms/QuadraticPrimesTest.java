package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuadraticPrimesTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(QuadraticPrimes.calculate(1000, 1000)).isEqualTo(-59231);
    }

}
