package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PandigitalProductSumTest
{
    @Test
    public void calculate()
    throws Exception
    {
        assertThat(PandigitalProductSum.calculate()).isEqualTo(45228);
    }

}
