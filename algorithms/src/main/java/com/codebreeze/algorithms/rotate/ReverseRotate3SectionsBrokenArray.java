package com.codebreeze.algorithms.rotate;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Arrays;
import java.util.function.BiConsumer;

/*
Here we assume we rotate around the part that's static
 */
public class ReverseRotate3SectionsBrokenArray<T> implements BiConsumer<T[], ReverseRotate3SectionsBrokenArray.RotationSpecs> {

    @Override
    public void accept(T[] elements, RotationSpecs rotationSpecs) {
        MutableInt stepCounter = new MutableInt();
        print(elements);
        reverseRotate(elements, rotationSpecs, stepCounter);
        print(elements);
        System.out.println(this.getClass().getSimpleName() + " : " + stepCounter);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    /* Alg 1: Rotate by reversal */
    private void reverse(T[] elements, Index i, Index j, MutableInt stepCounter) {
        while (i.currentValue() < j.currentValue()) {
            System.out.print("/");
            T t = elements[i.currentValue()];
            stepCounter.increment();
            elements[i.currentValue()] = elements[j.currentValue()];
            stepCounter.increment();
            print(elements);
            elements[j.currentValue()] = t;
            stepCounter.increment();
            print(elements);
            i.increment();
            j.decrement();
        }
    }

    private void reverseRotate(T[] elements, RotationSpecs rotationSpecs, MutableInt stepCounter) {
        reverse(
            elements,
            new CircularIndex(0, rotationSpecs.staticStart - 1, 0),
            new CircularIndex(0, rotationSpecs.staticStart - 1, rotationSpecs.staticStart - 1),
            stepCounter
        );
        reverse(
            elements,
            new CircularIndex(rotationSpecs.staticEnd + 1, elements.length - 1, rotationSpecs.staticEnd + 1),
            new CircularIndex(rotationSpecs.staticEnd + 1, elements.length - 1, elements.length - 1),
            stepCounter
        );
        reverse(
            elements,
            new SkipCircularIndex(0, elements.length - 1, rotationSpecs.staticStart, rotationSpecs.staticEnd, 0),
            new SkipCircularIndex(0, elements.length - 1, rotationSpecs.staticStart, rotationSpecs.staticEnd, elements.length - 1),
            stepCounter
        );
    }

    public static class RotationSpecs {
        private final int staticStart;
        private final int staticEnd;

        public RotationSpecs(int staticStart, int staticEnd) {
            this.staticStart = staticStart;
            this.staticEnd = staticEnd;
        }
    }

    private interface Index {
        int currentValue();

        void increment();

        void decrement();
    }

    // Note, you cannot skip if the start and end are the same! how can you skip in a 1 element indexing range.
    // but you can have the same startSkip and endSkip, because perhaps you are skipping one element in 3 elements array
    // also this implementation does not allow fo a 2 elements array cz then there is no skipping the middle section,
    // which is the main point of this implementation
    private static class SkipCircularIndex implements Index {
        private final int start;
        private final int end;
        private final int startToSkip;
        private final int endToSkip;
        private int currentValue;

        private SkipCircularIndex(int start, int end, int startToSkip, int endToSkip, int startingValue) {
            assert startToSkip <= endToSkip;
            assert start < startToSkip;
            assert end > endToSkip;
            assert startingValue >= start && startingValue <= end && (startingValue < startToSkip || startingValue > endToSkip);
            // apparently this is always true and so is unneeded as per my IDE
//            assert start <= end;
            this.start = start;
            this.end = end;
            this.startToSkip = startToSkip;
            this.endToSkip = endToSkip;
            this.currentValue = startingValue;
        }

        @Override
        public int currentValue() {
            return currentValue;
        }

        @Override
        public void increment() {

            if (currentValue + 1 == startToSkip) {
                currentValue = endToSkip + 1;
            } else {
                if (currentValue == end) {
                    currentValue = start;
                } else {
                    currentValue++;
                }
            }
        }

        @Override
        public void decrement() {
            if (currentValue - 1 == endToSkip) {
                currentValue = startToSkip - 1;
            } else {
                if (currentValue == start) {
                    currentValue = end;
                } else {
                    currentValue--;
                }
            }
        }
    }

    private static class CircularIndex implements Index {
        private final int start;
        private final int end;
        private int currentValue;

        private CircularIndex(int start, int end, int startingValue) {
            // apparently this is always true and so is unneeded as per my IDE
//            assert start <= end;
            this.start = start;
            this.end = end;
            this.currentValue = startingValue;
        }


        @Override
        public int currentValue() {
            return currentValue;
        }

        @Override
        public void increment() {
            if (currentValue == end) {
                currentValue = start;
            } else {
                currentValue++;
            }
        }

        @Override
        public void decrement() {
            if (currentValue == start) {
                currentValue = end;
            } else {
                currentValue--;
            }
        }
    }
}
