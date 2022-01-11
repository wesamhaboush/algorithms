package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.pair.IntIntPair;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CountingBinarySearchTest {

    @Test
    void apply() {
        // given
        int[] values = sortedValues();

        // when
        CountingBinarySearch cbs = new CountingBinarySearch(values);
        FasterBinarySearch fbs = new FasterBinarySearch(values);

        // then
        int sumForFast = 0;
        int sumForRegular = 0;
        for (int value : values) {
            IntIntPair regular = cbs.apply(value);
            IntIntPair fast = fbs.apply(value);
            sumForFast += fast.second();
            sumForRegular += regular.second();

            System.out.printf(
                "target=%d, regular[position=%d,opCount=%d], fast[position=%d,opCount=%d]%n",
                value,
                regular.first(),
                regular.second(),
                fast.first(),
                fast.second()
            );

            if (regular.first() != fast.first())
                System.out.printf(
                    "values[regular.first()]=%d, values[fast.first()]=%d%n",
                    values[regular.first()],
                    values[fast.first()]
                );
            assertThat(values[regular.first()]).isEqualTo(values[fast.first()]);
            assertThat(values[regular.first()]).isEqualTo(value);
        }

        assertThat(sumForFast).isLessThan(sumForRegular);

        System.out.println("regular total: " + sumForRegular);
        System.out.println("fast total: " + sumForFast);
    }

    private int[] sortedValues() {
        return IntStream.range(0, 1000)
            .map(i -> RandomUtils.nextInt(0, 10000))
            .sorted()
            .toArray();
    }
}
