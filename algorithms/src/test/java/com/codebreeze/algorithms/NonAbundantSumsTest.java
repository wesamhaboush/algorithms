package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NonAbundantSumsTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(NonAbundantSums.calculate(20)).isEqualTo(210L);
        assertThat(NonAbundantSums.calculate(40)).isEqualTo(620L);
        assertThat(NonAbundantSums.calculate(28123)).isEqualTo(4179871L);
    }

}
