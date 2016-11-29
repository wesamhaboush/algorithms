package com.codebreeze.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class PowerSetTest
{
    @Test
    public void of2()
    throws Exception
    {
        assertThat(PowerSet.of2(set())).containsExactlyInAnyOrder(set());
        assertThat(PowerSet.of2(set(1))).containsExactlyInAnyOrder(set(), set(1));
        assertThat(PowerSet.of2(set(1,2))).containsExactlyInAnyOrder(set(), set(1), set(2), set(1, 2));
        assertThat(PowerSet.of2(set(1,2,3))).containsExactlyInAnyOrder(set(), set(1), set(2), set(3),
                                                             set(1, 2), set(1,3), set(2, 3),
                                                             set(1,2,3));
    }

    @Test
    public void of()
    throws Exception
    {
        assertThat(PowerSet.of(set())).containsExactlyInAnyOrder(set());
        assertThat(PowerSet.of(set(1))).containsExactlyInAnyOrder(set(), set(1));
        assertThat(PowerSet.of(set(1,2))).containsExactlyInAnyOrder(set(), set(1), set(2), set(1, 2));
        assertThat(PowerSet.of(set(1,2,3))).containsExactlyInAnyOrder(set(), set(1), set(2), set(3),
                                                                       set(1, 2), set(1,3), set(2, 3),
                                                                       set(1,2,3));
    }

    private static Set<Integer> set(final Integer...ints)
    {
        return Arrays.stream(ints).collect(toSet());
    }
}
