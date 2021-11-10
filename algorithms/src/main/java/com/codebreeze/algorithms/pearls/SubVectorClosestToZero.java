package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.function.ToIntFunction;

import static java.lang.Math.abs;
import static java.util.Comparator.comparingInt;

public class SubVectorClosestToZero implements ToIntFunction<int[]> {
    @Override
    public int applyAsInt(int[] x) {
        Cumulative[] cumArray = new Cumulative[x.length];
        for (int i = 0; i < x.length; i++) {
            int sumUpToHere = (i == 0 ? 0 : cumArray[i - 1].value) + x[i]; // 0 used for the first element
            cumArray[i] = Cumulative.of(sumUpToHere, abs(sumUpToHere), i);
        }

        // sort based on absValue to ignore the sign, because we're focused on closeness to zero
        // not max value
        Arrays.sort(cumArray, comparingInt(a -> a.absValue)); // sort based on absValue to ignore the sign

        // for empty arrays, minToZero is zero!
        int minToZero = cumArray.length == 0 ? 0 : cumArray[0].value;

        // index of cumulative element where the difference with the previous is minimum
        int index = 0;
        for (int i = 0; i < cumArray.length; i++) {
            // previous if i == 0 is 0, nothing to subtract!
            int previousValue = i == 0 ? 0 : cumArray[i - 1].value;
            int currentValue = cumArray[i].value;
            int difference = currentValue - previousValue;
            if (abs(difference) < abs(minToZero)) {
                minToZero = difference;
                index = i;
            }
        }

        if (index != 0) { // don't do it for first element sum, it doesn't apply (see tests)
            if (cumArray[index].u > cumArray[index - 1].u) {
                minToZero = cumArray[index].value - cumArray[index - 1].value;
            } else {
                minToZero = cumArray[index - 1].value - cumArray[index].value;
            }
        }
        return minToZero;
    }

    private record Cumulative(int value, int absValue, int u) {
        static Cumulative of(int value, int absValue, int u) {
            return new Cumulative(value, absValue, u);
        }
    }
}
