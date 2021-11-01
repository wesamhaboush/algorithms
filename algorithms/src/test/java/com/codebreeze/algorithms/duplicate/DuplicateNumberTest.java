package com.codebreeze.algorithms.duplicate;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.function.ToIntFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

abstract class DuplicateNumberTest {

    @Test
    void applyAsInt() {
        // given
        // 12 is duplicate, 5 is missing
        int[] input1 = {0, 1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        // 2 is duplicate, 1 is missing
        int[] input2 = new int[]{2, 2, 3, 4};
        // 4 is duplicate, 10 is missing
        int[] input3 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 4, 11, 12, 13, 14, 15, 16};
        // no duplicates
        int[] input4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        // no elements
        int[] input5 = new int[]{};
        // one element
        int[] input6 = new int[]{2};

        // when
        ToIntFunction<int[]> duplicateNumberImpl = duplicateNumberImpl();

        // then
        assertThat(duplicateNumberImpl.applyAsInt(input1)).isEqualTo(12);
        assertThat(duplicateNumberImpl.applyAsInt(input2)).isEqualTo(2);
        assertThat(duplicateNumberImpl.applyAsInt(input3)).isEqualTo(4);
        assertThatCode(() -> duplicateNumberImpl.applyAsInt(input4)).isInstanceOf(RuntimeException.class);
        assertThatCode(() -> duplicateNumberImpl.applyAsInt(input5)).isInstanceOf(RuntimeException.class);
        assertThatCode(() -> duplicateNumberImpl.applyAsInt(input6)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void repeatToFindFailures() {
        for(int i = 0; i < 100; i++) {
            System.out.println("running test number " + i);
            applyAsInt_large_numbers();
        }
    }

    @Test
    void applyAsInt_large_numbers() {
        // given
        int max = new BigInteger("2").pow(20).intValue();
        int[] input = new int[1_050_000];

        for(int i = 0; i < input.length; i++) {
            input[i] = RandomUtils.nextInt(0, max);
        }
        // when
        ToIntFunction<int[]> duplicateNumberImpl = duplicateNumberImpl();
        int duplicate = duplicateNumberImpl.applyAsInt(input);

        // then
        assertThat(count(input, duplicate)).isGreaterThan(1);
    }

    private int count(int[] input, int duplicate) {
        int count = 0;
        for(int i : input) {
            if(i == duplicate) {
                count++;
            }
        }
        return count;
    }

    abstract ToIntFunction<int[]> duplicateNumberImpl();
}
