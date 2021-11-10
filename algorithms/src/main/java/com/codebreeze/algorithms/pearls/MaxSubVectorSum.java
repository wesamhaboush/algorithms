package com.codebreeze.algorithms.pearls;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntBiFunction;

import static com.codebreeze.algorithms.pearls.MaxSubVectorSum.DefaultMaximumForAllNegativeStrategy.*;
import static com.codebreeze.algorithms.pearls.MaxSubVectorSum.MaxSubVectorSumAlgorithm;
import static com.codebreeze.algorithms.pearls.MaxSubVectorSum.MaxSubVectorSumAlgorithm.*;
import static java.lang.Math.max;

public class MaxSubVectorSum implements ToIntBiFunction<int[], MaxSubVectorSumAlgorithm> {

    private static final Map<MaxSubVectorSumAlgorithm, ToIntBiFunction<int[], DefaultMaximumForAllNegativeStrategy>> implementations = new HashMap<>() {
        {
            put(COMPARE_ALL_RANGES, (ns, defaultMaximumForAllNegativeStrategy) -> {
                int maxSoFar = 0;
                if (defaultMaximumForAllNegativeStrategy.equals(MAX)) {
                    if (ns.length > 0) {
                        maxSoFar = ns[0];
                    }
                }
                for (int l = 0; l < ns.length; l++) {
                    int sum = 0;
                    for (int u = l; u < ns.length; u++) {
                        sum = sum + ns[u];
                        /* Sum now contains the sum of X[L..U] */
                        maxSoFar = max(maxSoFar, sum);
                    }
                }
                return maxSoFar;
            });
            put(CUMULATIVE_TABLE, (ns, defaultMaximumForAllNegativeStrategy) -> {
                int[] cumArray = new int[ns.length];
                for (int i = 0; i < ns.length; i++) {
                    cumArray[i] = (i == 0 ? 0 : cumArray[i - 1]) + ns[i];
                }
                int maxSoFar = 0;
                if (defaultMaximumForAllNegativeStrategy.equals(MAX)) {
                    if (ns.length > 0) {
                        maxSoFar = ns[0];
                    }
                }
                for (int l = 0; l < ns.length; l++) {
                    for (int u = l; u < ns.length; u++) {
                        int sum = cumArray[u] - (l == 0 ? 0 : cumArray[l - 1]);
                        /* Sum now contains the sum of X[L..U] */
                        maxSoFar = max(maxSoFar, sum);
                    }
                }
                return maxSoFar;
            });
            put(DIVIDE_AND_CONQUER, new ToIntBiFunction<int[], DefaultMaximumForAllNegativeStrategy>() {
                @Override
                public int applyAsInt(int[] ns, DefaultMaximumForAllNegativeStrategy defaultMaximumForAllNegativeStrategy) {
                    return maxSum(ns, 0, ns.length - 1, defaultMaximumForAllNegativeStrategy);
                }

                int maxSum(int[] ns, int l, int u, DefaultMaximumForAllNegativeStrategy defaultMaximumForAllNegativeStrategy) {
                    if (l > u) { /* zero element vector */
                        return 0;
                    }
                    if (l == u) { /* one-element vector */
                        if (defaultMaximumForAllNegativeStrategy.equals(MAX)) {
                            return ns[l];
                        } else {
                            return max(0, ns[l]);
                        }
                    }

                    int m = (l + u) / 2; /* A is X[L..M], B is X[M+1..U] */
                    /* Find max crossing to left */
                    int sumToLeft = 0;
                    int maxToLeft = 0;
                    if(defaultMaximumForAllNegativeStrategy.equals(MAX)) {
                        maxToLeft = ns[m];
                    }
                    for (int i = m; i >= l; i--) {
                        sumToLeft = sumToLeft + ns[i];
                        maxToLeft = max(maxToLeft, sumToLeft);
                    }
                    /* Find max crossing to right */
                    int sumToRight = 0;
                    int maxToRight = 0;
                    if(defaultMaximumForAllNegativeStrategy.equals(MAX)) {
                        maxToRight = ns[m + 1];
                    }
                    for (int i = m + 1; i <= u; i++) {
                        sumToRight = sumToRight + ns[i];
                        maxToRight = max(maxToRight, sumToRight);
                    }

                    int maxCrossing = maxToLeft + maxToRight;

                    int maxInA = maxSum(ns, l, m, defaultMaximumForAllNegativeStrategy);
                    int maxInB = maxSum(ns, m + 1, u, defaultMaximumForAllNegativeStrategy);
                    return max(max(maxCrossing, maxInA), maxInB);
                }
            });
            put(SCAN, (ns, defaultMaximumForAllNegativeStrategy) -> {
                int maxSoFar = 0;
                int maxEndingHere = 0;
                if (defaultMaximumForAllNegativeStrategy.equals(MAX)) {
                    if (ns.length > 0) {
                        maxSoFar = ns[0];
                    }
                }
                for (int n : ns) {
                    /* Invariant: MaxEndingHere and MaxSoFar are accurate for X[1..I-1] */
                    if (defaultMaximumForAllNegativeStrategy.equals(MAX)) {
                        maxEndingHere = max(maxEndingHere + n, n);
                    } else {
                        maxEndingHere = max(maxEndingHere + n, 0);
                    }
                    maxSoFar = max(maxSoFar, maxEndingHere);
                }
                return maxSoFar;
            });
        }
    };

    private final DefaultMaximumForAllNegativeStrategy defaultMaximumForAllNegativeStrategy;

    public MaxSubVectorSum(DefaultMaximumForAllNegativeStrategy defaultMaximumForAllNegativeStrategy) {
        this.defaultMaximumForAllNegativeStrategy = defaultMaximumForAllNegativeStrategy;
    }

    @Override
    public int applyAsInt(int[] ns, MaxSubVectorSumAlgorithm maxSubVectorSumAlgorithm) {
        return implementations.get(maxSubVectorSumAlgorithm).applyAsInt(ns, defaultMaximumForAllNegativeStrategy);
    }

    enum MaxSubVectorSumAlgorithm {
        COMPARE_ALL_RANGES,
        CUMULATIVE_TABLE,
        DIVIDE_AND_CONQUER,
        SCAN
    }

    enum DefaultMaximumForAllNegativeStrategy {
        ZERO,
        MAX
    }
}

