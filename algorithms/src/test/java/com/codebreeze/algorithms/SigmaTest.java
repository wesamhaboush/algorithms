package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SigmaTest
{
    @Test
    public void calculate() throws Exception
    {
        //1 + 2 + 11 + 22 = 34
        assertThat(Sigma.of(22L)).isEqualTo(36);
        //1 + 23 = 24
        assertThat(Sigma.of(23L)).isEqualTo(24);
        //1 + 2 + 3 + 4 + 6 + 8 + 12 + 24 = 60
        assertThat(Sigma.of(24L)).isEqualTo(60);
        //1 + 2 + 3 + 4 + 6 + 8 + 9 + 12 + 18 + 24 + 36 + 72 = 195
        assertThat(Sigma.of(72L)).isEqualTo(195);
    }

}
