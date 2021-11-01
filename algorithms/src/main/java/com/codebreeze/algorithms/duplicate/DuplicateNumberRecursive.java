package com.codebreeze.algorithms.duplicate;

import com.codebreeze.algorithms.bits.Bits;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

/*
Given a tape containing 1 050 000 twenty bit integers, how can you find one that appears at least twice?

2^20 = 1048576

The assumption of the problem here is that you know the max integer included. Also the assumption is that you have more
numbers than the max. So the max for us is from 0 to 1048576, but we have 1050 000 integers.

 */
public class DuplicateNumberRecursive implements ToIntFunction<int[]> {

    @Override
    public int applyAsInt(int[] values) {
        Result result = breakAroundMiddle(values, 0);
        if(result == Result.noDuplicate) {
            throw new RuntimeException("duplicates not found"); // should never happen in the problem statement, but anyway!
        } else {
            return result.duplicate;
        }
    }

    private Result breakAroundMiddle(int[] values, int separationBit) {
        if (values.length == 1) {
            return Result.noDuplicate;
        }
        if (values[0] == values[values.length - 1]) {
            return Result.of(values[0]); // any will do from that range because all of it is repeated!
        }
        List<Integer> zeros = new ArrayList<>();
        List<Integer> ones = new ArrayList<>();
        for (int i : values) {
            if (Bits.isSet(i, separationBit)) {
                ones.add(i);
            } else {
                zeros.add(i);
            }
        }
        if (zeros.size() > ones.size()) {
            return breakAroundMiddle(primitive(zeros), separationBit + 1);
        } else if (zeros.size() < ones.size()){
            return breakAroundMiddle(primitive(ones), separationBit + 1);
        } else {
            // if the bit that was chosen was not conclusive, use the next bit
            // perhaps here we have to start checking each branch separately and stop when we find a duplicate!
            Result resultZeros = breakAroundMiddle(primitive(zeros), separationBit + 1);
            if(resultZeros.hasDuplicate) {
                return  resultZeros;
            } else {
                return breakAroundMiddle(primitive(ones), separationBit + 1);
            }
        }
    }

    private int[] primitive(List<Integer> ones) {
        return ones.stream().mapToInt(Integer::intValue).toArray();
    }

    private static class Result {
        private static final Result noDuplicate = new Result(-1, false);

        private final int duplicate;
        private final boolean hasDuplicate;

        private Result(int duplicate, boolean hasDuplicate) {
            this.duplicate = duplicate;
            this.hasDuplicate = hasDuplicate;
        }

        private Result(int duplicate) {
            this(duplicate, true);
        }

        private static Result of(int duplicate) {
            return new Result(duplicate);
        }
    }
}
