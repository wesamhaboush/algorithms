package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LargestProductInGridTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(LargestProductInGrid.calculate(3)).isEqualTo(51267216);
    }

}
