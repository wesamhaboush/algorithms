package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class ReverseRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        print(elements);
        reverseRotate(elements, rotationDistance);
        print(elements);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    /* Alg 1: Rotate by reversal */
    private void reverse(T[] elements, int i, int j) {
        while (i < j) {
            System.out.print("/");
            T t = elements[i];
            elements[i] = elements[j];
            elements[j] = t;
            i++;
            j--;
        }
    }

    private void reverseRotate(T[] elements, int rotationDistance) {
        reverse(elements, 0, rotationDistance - 1);
        reverse(elements, rotationDistance, elements.length - 1);
        reverse(elements, 0, elements.length - 1);
    }
}
