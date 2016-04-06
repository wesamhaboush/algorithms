package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NthPrimeTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(NthPrime.calculate(6)).isEqualTo(13);
        assertThat(NthPrime.calculate(10001)).isEqualTo(104743);
    }

}
