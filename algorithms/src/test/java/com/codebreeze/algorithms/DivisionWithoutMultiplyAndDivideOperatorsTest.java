package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DivisionWithoutMultiplyAndDivideOperatorsTest
{
    @Test
    public void divide() throws Exception
    {
        assertThat(DivisionWithoutMultiplyAndDivideOperators.divide(12, 6)).isEqualTo(2);
    }

}
