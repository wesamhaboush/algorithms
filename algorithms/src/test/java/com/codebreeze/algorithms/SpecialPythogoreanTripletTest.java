package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialPythogoreanTripletTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(SpecialPythogoreanTriplet.calculate2(1000)).isEqualTo(31875000);
    }

}
