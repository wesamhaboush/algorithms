package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LexicographicPermutationsTest
{
    @Test
    public void calculate() throws Exception
    {
        // 0 = ABC, 1 = ACB, 2 = BAC, 3 = BCA, 4 = CAB, 5 = CBA
        assertThat(LexicographicPermutations.calculate("ABC", 5)).isEqualTo("CBA"); // 6th permutation (0 -> 5)
        assertThat(LexicographicPermutations.calculate("ABCD", 12)).isEqualTo("CABD"); //13th element (0 -> 12)
        assertThat(LexicographicPermutations.calculate("0123456789", 1000000 - 1)).isEqualTo("2783915460"); // the millionth element has index (1M - 1)
    }

}
