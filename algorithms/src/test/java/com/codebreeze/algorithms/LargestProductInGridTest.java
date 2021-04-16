package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LargestProductInGridTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(LargestProductInGrid.calculate(4)).isEqualTo(70600674L);
//        assertThat(LargestProductInGrid.calculate3()).isEqualTo(51267216);
    }

}
