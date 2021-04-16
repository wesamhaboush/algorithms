package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HighlyDivisibleTriangularNumberTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(HighlyDivisibleTriangularNumber.calculate(1)).isEqualTo(3); // 1, 3
        assertThat(HighlyDivisibleTriangularNumber.calculate(2)).isEqualTo(6); // 1, 2, 3, 6
        assertThat(HighlyDivisibleTriangularNumber.calculate(3)).isEqualTo(6); // 1, 2, 3, 6
        assertThat(HighlyDivisibleTriangularNumber.calculate(4)).isEqualTo(28); // 1, 2, 4, 7, 14, 28
        assertThat(HighlyDivisibleTriangularNumber.calculate(5)).isEqualTo(28); // 1, 2, 4, 7, 14, 28
        assertThat(HighlyDivisibleTriangularNumber.calculate(6)).isEqualTo(36); // 1, 2, 3, 4, 6, 9, 12, 18, 36
        assertThat(HighlyDivisibleTriangularNumber.calculate(7)).isEqualTo(36); // 1, 2, 3, 4, 6, 9, 12, 18, 36
        assertThat(HighlyDivisibleTriangularNumber.calculate(500)).isEqualTo(76576500);
    }
}
