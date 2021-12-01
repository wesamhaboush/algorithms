package com.codebreeze.algorithms.pearls;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.nanosToRun;
import static com.codebreeze.algorithms.pearls.SentinelLinearSearch.Algorithm.NoSentinel;
import static com.codebreeze.algorithms.pearls.SentinelLinearSearch.Algorithm.Sentinel;

class SentinelLinearSearchTest {

    @Test
    void test1() {
        // given
        final int[] elements = IntStream.range(0, 10000)
            .map(i -> RandomUtils.nextInt())
            .toArray();
        final int targetValue = RandomUtils.nextInt();

        // when
        SentinelLinearSearch nsls = new SentinelLinearSearch(elements, NoSentinel);
        SentinelLinearSearch sls = new SentinelLinearSearch(elements, Sentinel);

        // when
        Runnable nslsr = () -> nsls.test(targetValue);
        Runnable slsr = () -> sls.test(targetValue);
        System.out.println("sls: " + nanosToRun(slsr));
        System.out.println("nsls: " + nanosToRun(nslsr));
        System.out.println("sls: " + nanosToRun(slsr));
        System.out.println("nsls: " + nanosToRun(nslsr));
    }
}
