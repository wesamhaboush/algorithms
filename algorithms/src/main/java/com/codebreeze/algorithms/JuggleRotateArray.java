package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class JuggleRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        print(elements);
        juggleRot(elements, rotationDistance);
        print(elements);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    /* Alg 2: Juggling (dolphin) rotation */
    private void juggleRot(T[] elements, int rotationDistance) {
        int cycles = Gcd.FUNCTION.applyAsInt(rotationDistance, elements.length);
        for (int i = 0; i < cycles; i++) {
            System.out.print("/");
            /* move i-th values of blocks */
            T t = elements[i];
            int j = i;
            for (; ; ) {
                System.out.print("|");
                int k = j + rotationDistance;
                if (k >= elements.length) {
                    k -= elements.length;
                }
                if (k == i) {
                    break;
                }
                elements[j] = elements[k];
                j = k;
            }
            elements[j] = t;
        }
    }
}
