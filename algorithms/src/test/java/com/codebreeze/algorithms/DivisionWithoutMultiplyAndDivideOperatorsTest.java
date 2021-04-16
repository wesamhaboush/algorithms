package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DivisionWithoutMultiplyAndDivideOperatorsTest
{
    @Test
    public void divide() {
        assertThat(DivisionWithoutMultiplyAndDivideOperators.divide(12, 6)).isEqualTo(2);
    }

}
