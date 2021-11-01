package com.codebreeze.algorithms.duplicate;

import com.codebreeze.algorithms.bits.Bits;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.ToIntFunction;

/*
Given a tape containing 1 050 000 twenty bit integers, how can you find one that appears at least twice?

2^20 = 1048576

The assumption of the problem here is that you know the max integer included. Also the assumption is that you have more
numbers than the max. So the max for us is from 0 to 1048576, but we have 1050 000 integers.

 */
public class DuplicateNumberIterative implements ToIntFunction<int[]> {

    @Override
    public int applyAsInt(int[] values) {
        Result result = breakAroundMiddle(values);
        if (result == Result.noDuplicate) {
            throw new RuntimeException("duplicates not found"); // should never happen in the problem statement, but anyway!
        } else {
            return result.duplicate;
        }
    }

    private Result breakAroundMiddle(int[] values) {
        if (values.length == 0 || values.length == 1) {
            return Result.noDuplicate;
        }
        int separationBit = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(values);
        while (!queue.isEmpty()) {
            int[] currentValues = queue.poll();
            if (currentValues[0] == currentValues[currentValues.length - 1]) { // this catches two elements that are equal too
                return Result.of(currentValues[0]); // any will do from that range because all of it is repeated!
            } else {
                List<Integer> zeros = new ArrayList<>();
                List<Integer> ones = new ArrayList<>();
                int currentSeparationBit = separationBit;
                while (zeros.size() == 0 || ones.size() == 0) {
                    zeros.clear();
                    ones.clear();
                    for (int i : currentValues) {
                        if (Bits.isSet(i, currentSeparationBit)) {
                            ones.add(i);
                        } else {
                            zeros.add(i);
                        }
                    }
                    currentSeparationBit++;
                }
                separationBit++;
                if (zeros.size() > ones.size()) {
                    queue.clear(); // because everything else we queued becomes with no value to us as a larger group size with duplicate is found
                    queue.offer(primitive(zeros));
                } else if (zeros.size() < ones.size()) {
                    queue.clear(); // because everything else we queued becomes with no value to us as a larger group size with duplicate is found
                    queue.offer(primitive(ones));
                } else {
                    // Note, we do not clear here because it is inconclusive between the groups, so if we queues another group before, we still
                    // need to check it.
                    // if the bit that was chosen was not conclusive, use the next bit
                    // perhaps here we have to start checking each branch separately and stop when we find a duplicate!
                    // only queue if size greater than 1, otherwise, what is the point? no duplicates will be found in 1 item lists!
                    if (zeros.size() > 1) {
                        if (zeros.size() == 2) {
                            if (zeros.get(0).equals(zeros.get(1))) {
                                return Result.of(zeros.get(0));
                            }
                        } else {
                            queue.offer(primitive(zeros));
                        }
                    }
                    if (ones.size() > 1) {
                        if (ones.size() == 2 && !ones.get(0).equals(ones.get(1))) {
                            if (ones.get(0).equals(ones.get(1))) {
                                return Result.of(ones.get(0));
                            }
                        } else {
                            queue.offer(primitive(ones));
                        }
                    }
                }
            }
        }
        return Result.noDuplicate;
    }

    private int[] primitive(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static class Result {
        private static final Result noDuplicate = new Result(-1);

        private final int duplicate;

        private Result(int duplicate) {
            this.duplicate = duplicate;
        }

        private static Result of(int duplicate) {
            return new Result(duplicate);
        }
    }
}
