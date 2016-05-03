package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ThousandDigitFibonacciNumberTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(ThousandDigitFibonacciNumber.calculate(2)).isEqualTo(7);
        assertThat(ThousandDigitFibonacciNumber.calculate(3)).isEqualTo(12);
        assertThat(ThousandDigitFibonacciNumber.calculate(4)).isEqualTo(17);
        assertThat(ThousandDigitFibonacciNumber.calculate(1000)).isEqualTo(4782);
    }

}
