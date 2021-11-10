package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.pearls.MaxSubVectorSum.DefaultMaximumForAllNegativeStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.codebreeze.algorithms.pearls.MaxSubVectorSum.DefaultMaximumForAllNegativeStrategy.MAX;
import static com.codebreeze.algorithms.pearls.MaxSubVectorSum.DefaultMaximumForAllNegativeStrategy.ZERO;
import static com.codebreeze.algorithms.pearls.MaxSubVectorSum.MaxSubVectorSumAlgorithm;
import static org.assertj.core.api.Assertions.assertThat;

class MaxSubVectorSumTest {

    @Test
    void applyAsInt_empty() {
        // given
        int[] ns = {};

        // when/then

        testInputSum(ns, 0, ZERO);
        testInputSum(ns, 0, MAX);
    }

    @Test
    void applyAsInt_single_positive() {
        // given
        int[] ns = {8};

        // when/then

        testInputSum(ns, 8, ZERO);
        testInputSum(ns, 8, MAX);
    }

    @Test
    void applyAsInt_negative() {
        // given
        int[] ns1 = {-8};
        int[] ns2 = {-8, -9};
        int[] ns3 = {-8, -9, -1};

        // when/then

        testInputSum(ns1, 0, ZERO);
        testInputSum(ns1, -8, MAX);
        testInputSum(ns2, 0, ZERO);
        testInputSum(ns2, -8, MAX);
        testInputSum(ns3, 0, ZERO);
        testInputSum(ns3, -1, MAX);
    }

    @Test
    void applyAsInt_variety() {
        // given
        int[] ns1 = {-8, 1};
        int[] ns2 = {-8, 3, -9, 2, -1, 8};
        int[] ns3 = {15, -8, -9, -1, 6, -19, 80};
        int[] ns4 = {19, 15, -8, -9, -1, 6, -19, 80};
        int[] ns5 = {80, 15, -8, -9, -1, 6, -19};

        // when/then

        testInputSum(ns1, 1, ZERO);
        testInputSum(ns1, 1, MAX);
        testInputSum(ns2, 9, ZERO);
        testInputSum(ns2, 9, MAX);
        testInputSum(ns3, 80, ZERO);
        testInputSum(ns3, 80, MAX);
        testInputSum(ns4, 83, ZERO);
        testInputSum(ns4, 83, MAX);
        testInputSum(ns5, 95, ZERO);
        testInputSum(ns5, 95, MAX);
    }

    private void testInputSum(int[] ns, int sum, DefaultMaximumForAllNegativeStrategy defaultMaximumForAllNegativeStrategy) {
        MaxSubVectorSum maxSubVectorSum = new MaxSubVectorSum(defaultMaximumForAllNegativeStrategy);
        for (var maxSubVectorSumAlgorithm : MaxSubVectorSumAlgorithm.values()) {
            int actualSum = maxSubVectorSum.applyAsInt(ns, maxSubVectorSumAlgorithm);
            assertThat(actualSum)
                .withFailMessage(
                    () -> String.format("%s was being used, ns=%s, expected sum %d, but was %d",
                        maxSubVectorSumAlgorithm, Arrays.toString(ns), sum, actualSum
                    )
                )
                .isEqualTo(sum);
        }
    }
}
