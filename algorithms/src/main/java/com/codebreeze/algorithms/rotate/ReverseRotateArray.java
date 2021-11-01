package com.codebreeze.algorithms.rotate;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class ReverseRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        MutableInt stepCounter = new MutableInt();
        print(elements);
        reverseRotate(elements, rotationDistance, stepCounter);
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

    private void reverseRotate(T[] elements, int rotationDistance, MutableInt stepCounter) {
        reverse(elements, 0, rotationDistance - 1, stepCounter);
        reverse(elements, rotationDistance, elements.length - 1, stepCounter);
        reverse(elements, 0, elements.length - 1, stepCounter);
    }
}
