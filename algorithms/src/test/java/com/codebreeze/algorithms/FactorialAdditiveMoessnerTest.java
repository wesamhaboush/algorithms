package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactorialAdditiveMoessnerTest
{
    @Test
    public void factorial() throws Exception
    {
        assertThat(FactorialAdditiveMoessner.factorial(3)).isEqualTo(6);
        assertThat(FactorialAdditiveMoessner.factorial(4)).isEqualTo(24);
        assertThat(FactorialAdditiveMoessner.factorial(5)).isEqualTo(120);
        assertThat(FactorialAdditiveMoessner.factorial(6)).isEqualTo(720);
    }
}
