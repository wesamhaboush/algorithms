package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LargestCollatzProblemTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(LargestCollatzProblem.calculate(2)).isEqualTo(new long[]{1, 0});
        assertThat(LargestCollatzProblem.calculate(3)).isEqualTo(new long[]{2, 1});
        assertThat(LargestCollatzProblem.calculate(4)).isEqualTo(new long[]{3, 7});
        assertThat(LargestCollatzProblem.calculate(5)).isEqualTo(new long[]{3, 7});
        assertThat(LargestCollatzProblem.calculate(6)).isEqualTo(new long[]{3, 7});
        assertThat(LargestCollatzProblem.calculate(7)).isEqualTo(new long[]{6, 8});
        assertThat(LargestCollatzProblem.calculate(8)).isEqualTo(new long[]{7, 16});
        assertThat(LargestCollatzProblem.calculate(1000)).isEqualTo(new long[]{871, 178});
        assertThat(LargestCollatzProblem.calculate(2000)).isEqualTo(new long[]{1161, 181});
        assertThat(LargestCollatzProblem.calculate(10000)).isEqualTo(new long[]{6171L, 261L});
        assertThat(LargestCollatzProblem.calculate(100000)).isEqualTo(new long[]{77031L, 350L});
        assertThat(LargestCollatzProblem.calculate(200000)).isEqualTo(new long[]{156159L, 382L});
        assertThat(LargestCollatzProblem.calculate(400000)).isEqualTo(new long[]{230631L, 442L});
        assertThat(LargestCollatzProblem.calculate(600000)).isEqualTo(new long[]{511935L, 469L});
        assertThat(LargestCollatzProblem.calculate(800000)).isEqualTo(new long[]{626331L, 508L});
        assertThat(LargestCollatzProblem.calculate(900000)).isEqualTo(new long[]{837799L, 524L});
        assertThat(LargestCollatzProblem.calculate(1000000)).isEqualTo(new long[]{837799L, 524L});
    }
}
