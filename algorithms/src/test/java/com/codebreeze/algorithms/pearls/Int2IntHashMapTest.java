package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.Utils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;

class Int2IntHashMapTest {

    @Test
    void add() {
        // given
        // when
        Int2IntHashMap map = new Int2IntHashMap(1000);
        int[] values = range(0, 1000)
            .map(i -> nextInt(0, 10000))
            .toArray();
        Arrays.sort(values);
        for(int value : values) {
            map.add(value);
        }

        // then
        IntStream.range(0, 10)
            .mapToObj(i -> "run " + i + ": " + getTime(values, map))
            .sorted()
            .forEach(System.out::println);
    }

    private long getTime(int[] values, Int2IntHashMap bs) {
        return Utils.nanosToRun(() -> {
            for (int i : values) {
                assertThat(bs.find(i)).isNotEqualTo(-1);
            }
            assertThat(bs.find(-10)).isEqualTo(-1);
            assertThat(bs.find(20000)).isEqualTo(-1);
        });
    }
}
