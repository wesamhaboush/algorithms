package com.codebreeze.algorithms;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class LehmerCodePermutationTest {

    @Test
    public void of() throws Exception
    {
        assertThat(LehmerCodePermutation.of(asList(1, 2, 3)))
                .isEqualTo(
                        asList(
                                asList(1, 3, 2),
                                asList(2, 1, 3),
                                asList(3, 1, 2),
                                asList(1, 2, 3),
                                asList(2, 3, 1),
                                asList(3, 2, 1)
                        )
                );
        System.out.println(LehmerCodePermutation.of(asList(1, 2, 3)));
    }
}
