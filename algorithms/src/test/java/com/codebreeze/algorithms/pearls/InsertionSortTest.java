package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.nanosToRun;
import static com.codebreeze.algorithms.pearls.InsertionSort.Algorithm.Fast;
import static com.codebreeze.algorithms.pearls.InsertionSort.Algorithm.Slow;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;

class InsertionSortTest {

    @Test
    void accept() {
        // given
        final int[] x = IntStream.range(0, 1000)
            .map(i -> nextInt(0, 1000))
            .toArray();

        checkUnsorted(x);

        final int[] x1 = Arrays.copyOf(x, x.length);
        final int[] x2 = Arrays.copyOf(x, x.length);

        // when
        InsertionSort iss = new InsertionSort(Slow);
        InsertionSort isf = new InsertionSort(Fast);

        // then
        long slowTime = nanosToRun(() -> iss.accept(x1));
        long fastTime = nanosToRun(() -> isf.accept(x2));

        checkSorted(x1);
        checkSorted(x2);

        System.out.println("slow time: " + slowTime);
        System.out.println("fast time: " + fastTime);

        assertThat(slowTime).isGreaterThan(fastTime);
    }

    @Test
    void accept_short() {
        // given
        final int[] x = IntStream.range(0, 10)
            .map(i -> nextInt(0, 1000))
            .toArray();

        checkUnsorted(x);

        final int[] x1 = Arrays.copyOf(x, x.length);
        final int[] x2 = Arrays.copyOf(x, x.length);

        // when
//        InsertionSort.debug = true;
        InsertionSort iss = new InsertionSort(Slow);
        InsertionSort isf = new InsertionSort(Fast);

        // then
        long slowTime = nanosToRun(() -> iss.accept(x1));
        long fastTime = nanosToRun(() -> isf.accept(x2));

        checkSorted(x1);
        checkSorted(x2);

        System.out.println("slow time: " + slowTime);
        System.out.println("fast time: " + fastTime);
        assertThat(slowTime).isGreaterThan(fastTime);
    }

    private void checkUnsorted(int[] x) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] > x[i + 1]) {
                return;
            }
        }
        throw new IllegalStateException("sorted array received: " + Arrays.toString(x));
    }

    private void checkSorted(int[] x) {
        for (int i = 0; i < x.length - 1; i++) {
            if (x[i] > x[i + 1]) {
                throw new IllegalStateException("unsorted array received: " + Arrays.toString(x));
            }
        }
    }
}
