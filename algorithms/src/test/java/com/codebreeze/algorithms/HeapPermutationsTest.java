package com.codebreeze.algorithms;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class HeapPermutationsTest
{
    @Test
    public void of() throws Exception
    {
        assertThat(HeapPermutations.of(asList(1, 2, 3))).containsExactly(
                asList(1, 2, 3),
                asList(2, 1, 3),
                asList(3, 1, 2),
                asList(1, 3, 2),
                asList(2, 3, 1),
                asList(3, 2, 1)
                                                                        );
    }

}
