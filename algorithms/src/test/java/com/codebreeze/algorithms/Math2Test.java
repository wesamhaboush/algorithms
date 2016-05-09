package com.codebreeze.algorithms;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class Math2Test
{
    @Test
    public void findPerfectPower() throws Exception
    {
        assertThat(Math2.findPerfectPower(16)).isEqualTo(Math2.Exponent.from(2,4));
        assertThat(Math2.findPerfectPower(8)).isEqualTo(Math2.Exponent.from(2,3));
        assertThat(Math2.findPerfectPower(36)).isEqualTo(Math2.Exponent.from(6,2));
    }

    @Test
    public void divisorsOf() throws Exception
    {
        assertThat(Math2.divisorsOf(16, true, false)).containsOnly(1L, 2L, 4L, 8L);
        assertThat(Math2.divisorsOf(16, false, false)).containsOnly(2L, 4L, 8L);
        assertThat(Math2.divisorsOf(12, true, false)).containsOnly(1L, 2L, 3L, 4L, 6L);
        assertThat(Math2.divisorsOf(12, true, true)).containsOnly(1L, 2L, 3L, 4L, 6L, 12L);
        assertThat(Math2.divisorsOf(12, false, true)).containsOnly(2L, 3L, 4L, 6L, 12L);
    }

    @Test
    public void gcd() throws Exception
    {
        assertThat(Math2.gcd(2, 8)).isEqualTo(2L);
        assertThat(Math2.gcd(3, 8)).isEqualTo(1L);
        assertThat(Math2.gcd(3, 1)).isEqualTo(1L);
        assertThat(Math2.gcd(Math2.gcd(3, 9), 12)).isEqualTo(3L);
    }

    @Test
    public void countSetBits() throws Exception
    {
        assertThat(Math2.countSetBits(2)).isEqualTo(1);
        assertThat(Math2.countSetBits(7)).isEqualTo(3);
    }

    @Test
    public void isPerfectPower() throws Exception
    {
        assertThat(Math2.isPerfectPower(5)).isFalse();
        assertThat(Math2.isPerfectPower(8)).isTrue();
        assertThat(Math2.isPerfectPower(64)).isTrue();
        assertThat(Math2.isPerfectPower(65)).isFalse();
        assertThat(Math2.isPerfectPower(1000000)).isTrue();
        assertThat(Math2.isPerfectPower(1000001)).isFalse();
    }

    @Test
    public void subset() throws Exception
    {
        assertThat(Math2.subsets(asList(5))).isEqualTo(asSet(emptyList(),asList(5)));
        assertThat(Math2.subsets(asList(5,5))).isEqualTo(asSet(emptyList(),asList(5), asList(5, 5)));
        assertThat(Math2.subsets(asList(1,5,5))).isEqualTo(asSet(
                emptyList(),
                asList(5),
                asList(1),
                asList(1,5),
                asList(5, 5),
                asList(1,5, 5)));
        assertThat(Math2.subsets(asList(1,2,3))).isEqualTo(asSet(
                emptyList(),
                asList(1),
                asList(2),
                asList(3),
                asList(1,2),
                asList(1, 3),
                asList(2, 3),
                asList(1,2, 3)));
    }

    public <T> Set<T> asSet(T...ts)
    {
        return new HashSet<>(asList(ts));
    }
}
