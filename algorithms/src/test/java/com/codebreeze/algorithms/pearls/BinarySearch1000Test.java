package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.Utils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static com.codebreeze.algorithms.pearls.BinarySearch1000.Algorithm.*;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BinarySearch1000Test {

    @Test
    void applyAsInt() {
        // given
        int[] values = range(0, 1000)
            .map(i -> nextInt(0, 10000))
            .toArray();
        Arrays.sort(values);

        // when
        BinarySearch1000 bsa = new BinarySearch1000(values, A);
        BinarySearch1000 bsb = new BinarySearch1000(values, B);
        BinarySearch1000 bsc = new BinarySearch1000(values, C);
        BinarySearch1000 bsd = new BinarySearch1000(values, D);
        BinarySearch1000 bse = new BinarySearch1000(values, E);

        // then
        List<Supplier<String>> runnables = new ArrayList<>();
        runnables.add(() -> "a time: " + getTime(values, bsa));
        runnables.add(() -> "b time: " + getTime(values, bsb));
        runnables.add(() -> "c time: " + getTime(values, bsc));
        runnables.add(() -> "d time: " + getTime(values, bsd));
        runnables.add(() -> "e time: " + getTime(values, bse));
        Collections.shuffle(runnables);
        runnables.stream()
            .map(Supplier::get)
            .sorted()
            .forEach(System.out::println);
        System.out.println("=================");
        runnables.stream()
            .map(Supplier::get)
            .sorted()
            .forEach(System.out::println);
    }

    @Test
    void applyAsInt_failures() {
        assertThatThrownBy(() -> new BinarySearch1000(null, A)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new BinarySearch1000(new int[1000], null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new BinarySearch1000(new int[999], A)).isInstanceOf(IllegalArgumentException.class);
    }

    private long getTime(int[] values, BinarySearch1000 bs) {
        return Utils.nanosToRun(() -> {
            for (int i : values) {
                assertThat(bs.applyAsInt(i)).isNotEqualTo(-1);
            }
            assertThat(bs.applyAsInt(-10)).isEqualTo(-1);
            assertThat(bs.applyAsInt(20000)).isEqualTo(-1);
        });
    }
}
