package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactorialAdditiveMoessnerTest
{
    @Test
    void factorial() {
        assertThat(FactorialAdditiveMoessner.factorial(3)).isEqualTo(6);
        assertThat(FactorialAdditiveMoessner.factorial(4)).isEqualTo(24);
        assertThat(FactorialAdditiveMoessner.factorial(5)).isEqualTo(120);
        assertThat(FactorialAdditiveMoessner.factorial(6)).isEqualTo(720);
    }
}
