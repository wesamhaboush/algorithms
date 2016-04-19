package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LatticePathsTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(LatticePaths.calculate(2)).isEqualTo(6);
        assertThat(LatticePaths.calculate(4)).isEqualTo(70);
        assertThat(LatticePaths.calculate(10)).isEqualTo(184756L);
        assertThat(LatticePaths.calculate(20)).isEqualTo(137846528820L);
    }

}
