package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.pearls.TopNElements.Specs;
import com.codebreeze.algorithms.primitive.collections.list.IntList;
import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.IntSupplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TopNElementsTest {

    @Test
    void failures() {
        // null specs
        assertThatThrownBy(() -> new TopNElements().apply(null))
            .isInstanceOf(NullPointerException.class);

        // null supplier
        assertThatThrownBy(() -> new Specs(null, 10, 100))
            .isInstanceOf(NullPointerException.class);

        // topN > totalN
        assertThatThrownBy(() -> new Specs(() -> 1, 100, 10))
            .isInstanceOf(IllegalArgumentException.class);

        // topN < 1
        assertThatThrownBy(() -> new Specs(() -> 1, 0, 10))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Specs(() -> 1, -1, 10))
            .isInstanceOf(IllegalArgumentException.class);

        // totalN < -1
        assertThatThrownBy(() -> new Specs(() -> 1, 1, -1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Specs(() -> 1, 1, 0))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void apply_simple_case() {
        // given
        int topN = 3;
        int totalN = 10;
        IntList il = new ArrayIntList(totalN);
        IntSupplier is = () -> {
            int n = RandomUtils.nextInt(0, 10);
            il.add(n);
            return n;
        };

        // when
        int[] result = new TopNElements().apply(
            new Specs(is, topN, totalN)
        );

        // then
        int[] expected = il.stream().toArray();
        Arrays.sort(expected);
        expected = Arrays.copyOfRange(expected, expected.length - topN, expected.length);

        assertThat(expected).isEqualTo(result);
        System.out.println("all numbers: " + il);
        System.out.println("top N: " + Arrays.toString(result));
    }

    @Test
    void apply_huge_case() {
        // given
        int topN = 1000;
        int totalN = 10_000_000;
        IntList il = new ArrayIntList(totalN);
        IntSupplier is = () -> {
            int n = RandomUtils.nextInt(0, 10_000_000);
            il.add(n);
            return n;
        };

        // when
        int[] result = new TopNElements().apply(
            new Specs(is, topN, totalN)
        );

        // then
        int[] expected = il.stream().toArray();
        Arrays.sort(expected);
        expected = Arrays.copyOfRange(expected, expected.length - topN, expected.length);

        assertThat(expected).isEqualTo(result);
    }
}
