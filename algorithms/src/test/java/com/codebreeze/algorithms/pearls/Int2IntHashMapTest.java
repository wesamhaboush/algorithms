package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.Utils;
import com.codebreeze.algorithms.primitive.collections.map.Int2IntHashMap;
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
            map.put(value, value);
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
                assertThat(bs.get(i)).isNotEqualTo(-1);
            }
            assertThat(bs.get(-10)).isEqualTo(-1);
            assertThat(bs.get(20000)).isEqualTo(-1);
        });
    }
}
