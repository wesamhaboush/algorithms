package com.codebreeze.algorithms;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class MysteryPermutationTest
{
    @Test
    public void permute() throws Exception
    {
        assertThat(MysteryPermutation.permute(asList(1, 2, 3)))
        .containsOnly(
                asList(1,2,3),
                asList(1,3,2),
                asList(2,1,3),
                asList(2,3,1),
                asList(3,1,2),
                asList(3,2,1));
    }

}
