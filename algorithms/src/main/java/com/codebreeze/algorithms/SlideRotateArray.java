package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class SlideRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer I) {
        print(elements);
        slide(elements, I);
        print(elements);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    private void print(T t) {
        System.out.println(t);
    }

    private void slideNotSure(T[] elements, int rotdist) /* Benchmark: slide left rotdist (lose 0..rotdist-1) */ {
        for (int i = rotdist; i < elements.length; i++) {
            elements[i - rotdist] = elements[i];
            print(elements);
        }
    }

    private void slide(T[] elements, int rotationDistance) {
        for (int i = 0; i < rotationDistance; i++)
            leftRotatebyOne(elements);
    }

    private void leftRotatebyOne(T[] elements) {
        T temp = elements[0];
        // for (int i = 0; i < elements.length - 1; i++) {
        //            elements[i] = elements[i + 1];
        //        }
        System.arraycopy(elements, 1, elements, 0, elements.length - 1);
        elements[elements.length - 1] = temp;
    }
}
