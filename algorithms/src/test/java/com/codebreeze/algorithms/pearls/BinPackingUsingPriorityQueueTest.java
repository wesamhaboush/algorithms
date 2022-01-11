package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.pearls.BinPackingUsingPriorityQueue.Bin;
import com.codebreeze.algorithms.primitive.collections.iterable.ArrayDoubleIterable;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BinPackingUsingPriorityQueueTest {

    @Test
    void fail_for_null_input() {
        // given
        // when
        BinPackingUsingPriorityQueue bpupq = new BinPackingUsingPriorityQueue();

        // then
        assertThatThrownBy(() -> bpupq.apply(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void fail_for_input_values_larger_than_1_0() {
        // given
        // when
        BinPackingUsingPriorityQueue bpupq = new BinPackingUsingPriorityQueue();

        // then
        assertThatThrownBy(() -> bpupq.apply(new ArrayDoubleIterable(1.1))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bpupq.apply(new ArrayDoubleIterable(0.3, 1.1))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bpupq.apply(new ArrayDoubleIterable(0.3, 1.1, 0.5))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bpupq.apply(new ArrayDoubleIterable(1.1, 0.5))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void apply_vanilla() {
        // given
        ArrayDoubleIterable doubleIterable = new ArrayDoubleIterable(0.3, 0.9, 0.7, 0.2, 0.4, 0.5, 0.1, 1.0, 0.8);

        // when
        BinPackingUsingPriorityQueue bpupq = new BinPackingUsingPriorityQueue();
        Bin[] bins = bpupq.apply(doubleIterable);
        String[] lines = asLines(bins);

        // then
        assertThat(lines).hasSize(6);
        assertThat(lines).containsExactlyInAnyOrder(
            "Bin{remaining=0.20, boxes=[0.80]}",
            "Bin{remaining=0.50, boxes=[0.50]}",
            "Bin{remaining=0.10, boxes=[0.90]}",
            "Bin{remaining=0.00, boxes=[0.30,0.70]}",
            "Bin{remaining=0.00, boxes=[1.00]}",
            "Bin{remaining=0.30, boxes=[0.20,0.40,0.10]}"
        );
    }

    // Note, due to using doubles, numbers here might not fit exactly (i.e. there are a lot of approximations that
    // the toString is missing!!
    @Test
    void apply_vanilla2() {
        // given
        ArrayDoubleIterable doubleIterable = new ArrayDoubleIterable(0.1, 0.1, 0.1, 0.2, 0.2, 0.1, 0.1, 1.0, 0.1);

        // when
        BinPackingUsingPriorityQueue bpupq = new BinPackingUsingPriorityQueue();
        Bin[] bins = bpupq.apply(doubleIterable);
        String[] lines = asLines(bins);

        // then
        assertThat(lines).hasSize(3);
        assertThat(lines).containsExactlyInAnyOrder(
            "Bin{remaining=0.90, boxes=[0.10]}",
            "Bin{remaining=0.10, boxes=[0.10,0.10,0.10,0.20,0.20,0.10,0.10]}",
            "Bin{remaining=0.00, boxes=[1.00]}"
        );
    }

    @Test
    void apply_large() {
        // given
        ArrayDoubleIterable doubleIterable = new ArrayDoubleIterable(
            IntStream.range(0, 100000).mapToDouble(i -> Math.random()).toArray()
        );

        // when
        BinPackingUsingPriorityQueue bpupq = new BinPackingUsingPriorityQueue();
        Bin[] bins = bpupq.apply(doubleIterable);

        // then
        long emptyishBinCount = Arrays.stream(bins).filter(bin -> bin.remaining() < .25).count();
        assertThat(emptyishBinCount).isGreaterThan(40000);
        assertThat(bins.length).isLessThan(75000);
    }

    @Test
    void apply_single_value_fits_in_one_bin() {
        // given
        ArrayDoubleIterable doubleIterable = new ArrayDoubleIterable(0.3);

        // when
        BinPackingUsingPriorityQueue bpupq = new BinPackingUsingPriorityQueue();
        Bin[] bins = bpupq.apply(doubleIterable);
        String[] lines = asLines(bins);

        // then
        assertThat(lines).hasSize(1);
        assertThat(lines).containsExactly(
            "Bin{remaining=0.70, boxes=[0.30]}"
        );
    }

    @Test
    void apply_single_value_fits_in_one_bin_full_bin() {
        // given
        ArrayDoubleIterable doubleIterable = new ArrayDoubleIterable(1.0);

        // when
        BinPackingUsingPriorityQueue bpupq = new BinPackingUsingPriorityQueue();
        Bin[] bins = bpupq.apply(doubleIterable);
        String[] lines = asLines(bins);

        // then
        assertThat(lines).hasSize(1);
        assertThat(lines).containsExactly(
            "Bin{remaining=0.00, boxes=[1.00]}"
        );
    }

    @Test
    void apply_empty_iterator_results_in_no_bins() {
        // given
        ArrayDoubleIterable doubleIterable = new ArrayDoubleIterable();

        // when
        BinPackingUsingPriorityQueue bpupq = new BinPackingUsingPriorityQueue();
        Bin[] bins = bpupq.apply(doubleIterable);

        // then
        assertThat(bins).hasSize(0);
    }

    private String[] asLines(Bin[] bins) {
        return Arrays.stream(bins).map(String::valueOf).toArray(String[]::new);
    }

    private void print(Bin[] result) {
        Arrays.stream(result).forEachOrdered(System.out::println);
    }
}
