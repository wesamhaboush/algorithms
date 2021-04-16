package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindLargest13NumberSequenceTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(FindLargest13NumberSequence.calculate(4).maxProduct).isEqualTo(5832);
        FindLargest13NumberSequence.Triple result = FindLargest13NumberSequence.calculate(13);
        assertThat(result.maxProduct).isEqualTo(23514624000L);
        assertThat(result.startIndex).isEqualTo(197);
        assertThat(result.endIndex).isEqualTo(209);
    }

}
