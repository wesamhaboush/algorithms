package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ExeterPermutationTest
{
    @Test
    public void of() throws Exception
    {
        assertThat(ExeterPermutation.of(asList(1, 2, 3))).containsOnly(
                asList(1, 2, 3),
                asList(1, 3, 2),
                asList(2, 1, 3),
                asList(2, 3, 1),
                asList(3, 2, 1),
                asList(3, 1, 2)
                                                                      );
    }

}
