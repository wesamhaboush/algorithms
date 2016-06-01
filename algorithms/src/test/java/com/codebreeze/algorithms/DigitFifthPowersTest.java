package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DigitFifthPowersTest
{
    @Test
    public void calculate() throws Exception
    {
        System.out.println("for 1:");
        assertThat(DigitFifthPowers.calculate(1)).isEqualTo(45L);
        System.out.println("for 2:");
        assertThat(DigitFifthPowers.calculate(2)).isEqualTo(1L);
        System.out.println("for 3:");
        assertThat(DigitFifthPowers.calculate(3)).isEqualTo(1302L);
        System.out.println("for 4:");
        assertThat(DigitFifthPowers.calculate(4)).isEqualTo(19317L);
        System.out.println("for 5:");
        assertThat(DigitFifthPowers.calculate(5)).isEqualTo(443840L);
        System.out.println("for 5 but without 1:");
        assertThat(DigitFifthPowers.calculate(5) - 1).isEqualTo(443839L);
    }
}
