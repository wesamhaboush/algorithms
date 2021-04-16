package com.codebreeze.algorithms;


import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CoinSumTest {
    @Test
    public void calculateMemoized() {
        final long start = System.nanoTime();
        //f(5, {2, 1}) = {
        // {1,1,1,1,1},
        // {1,1,1,2},
        // {1,2,2}
        // }
        assertThat(CoinSum.calculateMemoized(5, new HashSet<>(asList(2L, 1L)))).isEqualTo(3);
        //f(5, {5, 2, 1}) = {
        // {1,1,1,1,1},
        // {1,1,1,2},
        // {1,2,2},
        // {5}
        // }
        assertThat(CoinSum.calculateMemoized(5, new HashSet<>(asList(5L, 2L, 1L)))).isEqualTo(4);
        assertThat(CoinSum.calculateMemoized(200, new HashSet<>(asList(200L, 100L, 50L, 20L, 10L, 5L, 2L, 1L)))).isEqualTo(73682L);
        final long end = System.nanoTime();
        System.out.println("memoized: " + TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS));
    }

    @Test
    public void calculateUnmemoized() {
        final long start = System.nanoTime();
        //f(5, {2, 1}) = {
        // {1,1,1,1,1},
        // {1,1,1,2},
        // {1,2,2}
        // }
        assertThat(CoinSum.calculateUnmemoized(5, new HashSet<>(asList(2L, 1L)))).isEqualTo(3);
        //f(5, {5, 2, 1}) = {
        // {1,1,1,1,1},
        // {1,1,1,2},
        // {1,2,2},
        // {5}
        // }
        assertThat(CoinSum.calculateUnmemoized(5, new HashSet<>(asList(5L, 2L, 1L)))).isEqualTo(4);
        assertThat(CoinSum.calculateUnmemoized(200, new HashSet<>(asList(200L, 100L, 50L, 20L, 10L, 5L, 2L, 1L)))).isEqualTo(73682L);
        final long end = System.nanoTime();
        System.out.println("unmemoized: " + TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS));
    }
}
