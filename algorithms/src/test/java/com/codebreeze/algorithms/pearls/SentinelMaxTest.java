package com.codebreeze.algorithms.pearls;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.nanosToRun;
import static com.codebreeze.algorithms.pearls.SentinelMax.Algorithm.NoSentinel;
import static com.codebreeze.algorithms.pearls.SentinelMax.Algorithm.Sentinel;
import static org.assertj.core.api.Assertions.assertThat;

class SentinelMaxTest {

    @Test
    void getAsInt() {
        // given
        final int[] elements = IntStream.range(0, 10000)
            .map(i -> RandomUtils.nextInt())
            .toArray();

        // when
        SentinelMax nsm = new SentinelMax(elements, NoSentinel);
        SentinelMax sm = new SentinelMax(elements, Sentinel);

        // when
        Runnable nsmr = nsm::getAsInt;
        Runnable smr = sm::getAsInt;
        System.out.println("nsmr: " + nanosToRun(nsmr));
        System.out.println("smr: " + nanosToRun(smr));
        System.out.println("nsmr: " + nanosToRun(nsmr));
        System.out.println("smr: " + nanosToRun(smr));
    }

    @Test
    void getAsInt_() {
        // given
        final int[] elements = IntStream.range(0, 5)
            .map(i -> RandomUtils.nextInt(0, 10))
            .toArray();

        // when
        SentinelMax sm = new SentinelMax(elements, Sentinel);
        SentinelMax nsm = new SentinelMax(elements, NoSentinel);

        // when

        int sentinelResult = sm.getAsInt();
        int nonSentinelResult = nsm.getAsInt();
        assertThat(sentinelResult).isEqualTo(nonSentinelResult);
        for(int element : elements) {
            assertThat(element).isLessThanOrEqualTo(sentinelResult);
        }
        System.out.println("elements: " + Arrays.toString(elements) + ", max: " + sentinelResult);
    }
}
