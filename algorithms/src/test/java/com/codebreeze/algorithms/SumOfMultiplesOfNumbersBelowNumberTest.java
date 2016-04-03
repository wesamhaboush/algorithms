package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumOfMultiplesOfNumbersBelowNumberTest
{
    @Test
    public void calculate() throws Exception
    {
        // 3 + 5 + 6 + 9 = 23
        assertThat(SumOfMultiplesOfNumbersBelowNumber.calculate(10, new int[]{3,5})).isEqualTo(23);
        assertThat(SumOfMultiplesOfNumbersBelowNumber.calculate(1000, new int[]{3, 5})).isEqualTo(233168);
        // 2 + 4 + 6 + 7 + 8 + 10 + 12 + 14+ 16 + 18 = 97
        assertThat(SumOfMultiplesOfNumbersBelowNumber.calculate(20, new int[]{2, 7})).isEqualTo(97);
    }

}
