package com.codebreeze.algorithms.rotate;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class SlideRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        MutableInt stepCounter = new MutableInt();
        print(elements);
        slide(elements, rotationDistance, stepCounter);
        print(elements);
        System.out.println(this.getClass().getSimpleName() + " : " + stepCounter);
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

    private void slide(T[] elements, int rotationDistance, MutableInt stepCounter) {
        for (int i = 0; i < rotationDistance; i++) {
            System.out.print('/');
            leftRotatebyOne(elements, stepCounter);
        }
    }

    private void leftRotatebyOne(T[] elements, MutableInt stepCounter) {
        T temp = elements[0];
        stepCounter.increment();
        for (int i = 0; i < elements.length - 1; i++) {
            System.out.print('|');
            elements[i] = elements[i + 1];
            stepCounter.increment();
            print(elements);
        }
//        System.arraycopy(elements, 1, elements, 0, elements.length - 1);
        elements[elements.length - 1] = temp;
        stepCounter.increment();
        print(elements);
    }
}
