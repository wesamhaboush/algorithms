package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class SlideRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        print(elements);
        slide(elements, rotationDistance);
        print(elements);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    private void slideNotSure(T[] elements, int rotationDistance) /* Benchmark: slide left rotdist (lose 0..rotdist-1) */ {
        for (int i = rotationDistance; i < elements.length; i++) {
            elements[i - rotationDistance] = elements[i];
            print(elements);
        }
    }

    private void slide(T[] elements, int rotationDistance) {
        for (int i = 0; i < rotationDistance; i++) {
            System.out.print('/');
            leftRotatebyOne(elements);
        }
    }

    private void leftRotatebyOne(T[] elements) {
        T temp = elements[0];
        for (int i = 0; i < elements.length - 1; i++) {
            System.out.print('|');
            elements[i] = elements[i + 1];
        }
//        System.arraycopy(elements, 1, elements, 0, elements.length - 1);
        elements[elements.length - 1] = temp;
    }
}
