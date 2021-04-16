package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DigitCancellingFractionsTest
{
    @Test
    public void calculate() {
        assertThat(DigitCancellingFractions.calculate()).isEqualTo(100);
    }

}
