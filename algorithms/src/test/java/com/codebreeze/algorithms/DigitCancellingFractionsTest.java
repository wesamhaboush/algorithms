package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DigitCancellingFractionsTest
{
    @Test
    public void calculate()
    throws Exception
    {
        assertThat(DigitCancellingFractions.calculate()).isEqualTo(100);
    }

}
