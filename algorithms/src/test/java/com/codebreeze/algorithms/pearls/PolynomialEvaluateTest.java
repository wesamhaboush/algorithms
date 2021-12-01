package com.codebreeze.algorithms.pearls;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Utils.nanosToRun;
import static com.codebreeze.algorithms.pearls.PolynomialEvaluate.Algorithm.N;
import static com.codebreeze.algorithms.pearls.PolynomialEvaluate.Algorithm.NN;
import static org.assertj.core.api.Assertions.assertThat;

class PolynomialEvaluateTest {

    @Test
    void applyAsInt() {
        // given
        final int[] elements = IntStream.range(0, 20)
            .map(i -> RandomUtils.nextInt(0, 10))
            .toArray();
        final int x = 5;

        // when
        PolynomialEvaluate n = new PolynomialEvaluate(elements, N);
        PolynomialEvaluate nn = new PolynomialEvaluate(elements, NN);

        // when
        assertThat(n.applyAsInt(x)).isEqualTo(nn.applyAsInt(x));
        Runnable nr = () -> n.applyAsInt(x);
        Runnable nnr = () -> nn.applyAsInt(x);
        System.out.println("nr: " + nanosToRun(nr));
        System.out.println("nnr: " + nanosToRun(nnr));
        System.out.println("nr: " + nanosToRun(nr));
        System.out.println("nnr: " + nanosToRun(nnr));
        System.out.println("nr: " + nanosToRun(nr));
        System.out.println("nnr: " + nanosToRun(nnr));
        System.out.println("nr: " + nanosToRun(nr));
        System.out.println("nnr: " + nanosToRun(nnr));
    }
}
