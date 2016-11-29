package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DigitFifthPowersTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(DigitFifthPowers.calculate(1)).isEqualTo(45L);
        assertThat(DigitFifthPowers.calculate(2)).isEqualTo(1L);
        assertThat(DigitFifthPowers.calculate(3)).isEqualTo(1302L);
        assertThat(DigitFifthPowers.calculate(4)).isEqualTo(19317L);
        assertThat(DigitFifthPowers.calculate(5)).isEqualTo(443840L);
        assertThat(DigitFifthPowers.calculate(5) - 1).isEqualTo(443839L);
    }
}
