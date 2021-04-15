package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class ReverseRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer I) {
        print(elements);
        revrot(elements, I);
        print(elements);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    private void print(T t) {
        System.out.println(t);
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

    private void revrot(T[] elements, int rotdist) {
        reverse(elements, 0, rotdist - 1);
        reverse(elements, rotdist, elements.length - 1);
        reverse(elements, 0, elements.length - 1);
    }
}
