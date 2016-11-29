package com.codebreeze.algorithms;

import org.junit.Test;

import java.util.Stack;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class HanoiTowerTest
{
    @Test
    public void hanoi_tower_works_for_three_disks() throws Exception
    {
        //given
        final Stack<Integer> source = stack(1, 2, 3);
        final Stack<Integer> destination = new Stack<>();
        final Stack<Integer> auxiliary = new Stack<>();
        //when
        HanoiTower.of(source, destination, auxiliary);
        //then
        assertThat(source).isEmpty();
        assertThat(destination).isEqualTo(stack(1,2,3));
        assertThat(auxiliary).isEmpty();
    }

    private static final  <T> Stack<T> stack(final T...ts)
    {
        final Stack<T> s = new Stack<>();
        s.addAll(asList(ts));
        return s;
    }
}
