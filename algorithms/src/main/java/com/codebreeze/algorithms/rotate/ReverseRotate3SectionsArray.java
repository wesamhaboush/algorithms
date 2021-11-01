package com.codebreeze.algorithms.rotate;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class ReverseRotate3SectionsArray<T> implements BiConsumer<T[], ReverseRotate3SectionsArray.RotationSpecs> {

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
    private void reverse(T[] elements, int i, int j, MutableInt stepCounter) {
        while (i < j) {
            System.out.print("/");
            T t = elements[i];
            stepCounter.increment();
            elements[i] = elements[j];
            stepCounter.increment();
            print(elements);
            elements[j] = t;
            stepCounter.increment();
            print(elements);
            i++;
            j--;
        }
    }

    // A: 0 to startStatic - 1
    // B: startStatic to endStatic
    // C: endStatic + 1 to elements.length - 1
    // to shift AB to BA, we do:
    // reverse from 0 to A-end
    // reverse from A-end + 1 to BA-length - 1
    // reverse from 0 to AB-length - 1
    // let's apply this three times to do ABC to CBA, ie. via:
    // ABC -> BAC -> BCA -> CBA
    private void reverseRotate(T[] elements, RotationSpecs rotationSpecs, MutableInt stepCounter) {
        int aLength = rotationSpecs.staticStart;
        int bLength = rotationSpecs.staticEnd - rotationSpecs.staticStart + 1;
        int cLength = elements.length - rotationSpecs.staticEnd - 1;

        reverse(elements, 0, aLength - 1, stepCounter);
        reverse(elements, rotationSpecs.staticStart, rotationSpecs.staticEnd, stepCounter);
        reverse(elements, 0, aLength + bLength - 1, stepCounter);

        reverse(elements, bLength, bLength + aLength - 1, stepCounter);
        reverse(elements, aLength + bLength, elements.length - 1, stepCounter);
        reverse(elements, bLength, elements.length - 1, stepCounter);

        reverse(elements, 0, bLength - 1, stepCounter);
        reverse(elements, bLength, bLength + cLength - 1, stepCounter);
        reverse(elements, 0, bLength + cLength - 1, stepCounter);
    }

    public static class RotationSpecs {
        private final int staticStart;
        private final int staticEnd;

        public RotationSpecs(int staticStart, int staticEnd) {
            this.staticStart = staticStart;
            this.staticEnd = staticEnd;
        }
    }
}
