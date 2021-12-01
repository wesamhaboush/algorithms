package com.codebreeze.algorithms.pearls;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.nanosToRun;
import static com.codebreeze.algorithms.pearls.QuickSort.Algorithm.ALG1;
import static com.codebreeze.algorithms.pearls.QuickSort.Algorithm.ALG2;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.*;

class QuickSortTest {

    @Test
    void accept_failures() {
        assertThatThrownBy(() -> new QuickSort(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new QuickSort(ALG1).accept(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void accept_empty() {
        // given
        int[] emptyX = {};

        // when
        QuickSort a1qs = new QuickSort(ALG1);
        QuickSort a2qs = new QuickSort(ALG2);

        // then
        assertThatCode(() -> a1qs.accept(emptyX)).doesNotThrowAnyException();
        assertThatCode(() -> a2qs.accept(emptyX)).doesNotThrowAnyException();
    }

    @Test
    void accept_one() {
        // given
        int[] expected = {1};
        int[] x1 = {1};
        int[] x2 = {1};

        // when
        QuickSort a1qs = new QuickSort(ALG1);
        QuickSort a2qs = new QuickSort(ALG2);
        a1qs.accept(x1);
        a2qs.accept(x2);

        // then
        assertThat(x1).isEqualTo(expected);
        assertThat(x2).isEqualTo(expected);
    }

    @Test
    void accept_two() {
        // given
        int[] x2sorted1 = {-1, 2};
        int[] x2sorted2 = {-1, 2};
        int[] x2unsorted1 = {2, -1};
        int[] x2unsorted2 = {2, -1};
        int[] x2expected = {-1, 2};

        // when
        QuickSort a1qs = new QuickSort(ALG1);
        QuickSort a2qs = new QuickSort(ALG2);
        a1qs.accept(x2sorted1);
        a1qs.accept(x2unsorted1);
        a2qs.accept(x2sorted2);
        a2qs.accept(x2unsorted2);

        // then
        assertThat(x2sorted1).isEqualTo(x2expected);
        assertThat(x2unsorted1).isEqualTo(x2expected);
        assertThat(x2sorted2).isEqualTo(x2expected);
        assertThat(x2unsorted2).isEqualTo(x2expected);
    }

    @Test
    void accept_many() {
        // when
        QuickSort a1qs = new QuickSort(ALG1);
        QuickSort a2qs = new QuickSort(ALG2);

        IntStream.range(3, 13)
            .forEach(
                length -> {
                    // given
                    int[] x = intArrayOfSize(length);
                    int[] xSorted = Arrays.copyOf(x, x.length);
                    Arrays.sort(xSorted);

                    // when
                    System.out.println("sorting: " + Arrays.toString(x));
                    System.out.println("sorted: " + Arrays.toString(xSorted));
                    int[] x1 = Arrays.copyOf(x, x.length);
                    int[] x2 = Arrays.copyOf(x, x.length);
                    a1qs.accept(x1);
                    a2qs.accept(x2);

                    // then
                    assertThat(x1).isEqualTo(xSorted);
                    assertThat(x2).isEqualTo(xSorted);
                }
            );
    }

    @Test
    void accept_many_performance() {
//        accept_many_performance(() -> intArrayOfSize(100)); // this unfortunately does NOT work (low cardinality?)
        accept_many_performance(() -> intArrayOfSize(1000));
//        accept_many_performance(() -> intArrayOfSize(100, 20)); // this unfortunately does NOT work (low cardinality?)
        accept_many_performance(() -> intArrayOfSize(1000, 200));
    }

    void accept_many_performance(Supplier<int[]> startingArray) {
        int[] x = startingArray.get();
        QuickSort a1qs = new QuickSort(ALG1);
        QuickSort a2qs = new QuickSort(ALG2);
        int[] xSorted = Arrays.copyOf(x, x.length);

        Arrays.sort(xSorted);

        System.out.println("sorting: " + Arrays.toString(x));
        System.out.println("sorted: " + Arrays.toString(xSorted));

        int sampleCount = 10;
        DescriptiveStatistics alg1Times = new DescriptiveStatistics();
        DescriptiveStatistics alg2Times = new DescriptiveStatistics();

        IntStream.range(0, sampleCount + 1).forEach(i_ -> {
            long alg1Time = nanosToRun(() -> testSorting(a1qs, Arrays.copyOf(x, x.length), xSorted));
            long alg2Time = nanosToRun(() -> testSorting(a2qs, Arrays.copyOf(x, x.length), xSorted));
            if(i_ != 0) { // this is to avoid first value which is normally an outlier!
                alg1Times.addValue(alg1Time);
                alg2Times.addValue(alg2Time);
            }
        });
        System.out.println("algorithm 1: " + alg1Times);
        System.out.println("algorithm 2: " + alg2Times);
        assertThat(alg1Times.getPercentile(30)).isGreaterThan(alg2Times.getPercentile(30));
        assertThat(alg1Times.getSum()).isGreaterThan(alg2Times.getSum());
    }

    private void testSorting(QuickSort qs, int[] x, int[] xSorted) {
        int[] x1 = Arrays.copyOf(x, x.length);
        qs.accept(x1);
        // then
        assertThat(x1).isEqualTo(xSorted);
    }

    private int[] intArrayOfSize(int length) {
        int[] x = new int[length];
        for (int i = 0; i < x.length; i++) {
            x[i] = nextBoolean() ? -nextInt() : nextInt();
        }
        return x;
    }

    private int[] intArrayOfSize(int length, int value) {
        int[] x = new int[length];
        Arrays.fill(x, value);
        return x;
    }
}
