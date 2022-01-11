package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.Utils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.DoubleStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FloatSumTest {

    @Test
    void applyAsDouble_simple_case_large() {
        Utils.retry(10, Set.of(AssertionError.class), () -> {
            // given
            double[] values = DoubleStream.generate(() -> RandomUtils.nextDouble(0, 10000.0))
                .limit(10000)
                .toArray();

            // when
            FloatSum fs = new FloatSum();
            double morePreciseResult = fs.applyAsDouble(values);
            double lessPreciseResult = Arrays.stream(values).sum();

            // then
            assertThat(lessPreciseResult).isNotEqualTo(morePreciseResult);
            System.out.println("more precise: " + morePreciseResult);
            System.out.println("less precise: " + lessPreciseResult);
        });
    }

    @Test
    void applyAsDouble_simple_case_small() {
        Utils.retry(10, Set.of(AssertionError.class), () -> {
            // given
            double[] values = DoubleStream.generate(() -> RandomUtils.nextDouble(0, 10000.0))
                .limit(10)
                .toArray();
            System.out.println(Arrays.toString(values));

            // when
            FloatSum fs = new FloatSum();
            double morePreciseResult = fs.applyAsDouble(values);
            double lessPreciseResult = Arrays.stream(values).sum();

            // then
            assertThat(lessPreciseResult).isNotEqualTo(morePreciseResult);
            System.out.println("more precise: " + morePreciseResult);
            System.out.println("less precise: " + lessPreciseResult);
        });
    }

    @Test
    void apply_null_input() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new FloatSum().applyAsDouble(null))
            .isInstanceOf(NullPointerException.class);
    }
}
