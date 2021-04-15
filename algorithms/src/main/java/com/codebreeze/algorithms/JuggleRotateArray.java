package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class JuggleRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer I) {
        print(elements);
        juggleRot(elements, I);
        print(elements);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    private void print(T t) {
        System.out.println(t);
    }

    /* Alg 2: Juggling (dolphin) rotation */
    private void juggleRot(T[] elements, int I) {
        int cycles = gcd(I, elements.length);
        for (int i = 0; i < cycles; i++) {
            System.out.print("/");
            /* move i-th values of blocks */
            T t = elements[i];
            int j = i;
            for (; ; ) {
                System.out.print("|");
                int k = j + I;
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

    private int gcd(int i, int j) {
        while (i != 0) {
            if (j >= i) {
                j -= i;
            } else {
                int t = i;
                i = j;
                j = t;
            }
        }
        return j;
    }
}
