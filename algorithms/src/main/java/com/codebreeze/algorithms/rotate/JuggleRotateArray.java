package com.codebreeze.algorithms.rotate;

import com.codebreeze.algorithms.Gcd;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class JuggleRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        MutableInt stepCounter = new MutableInt();
        print(elements);
        juggleRot(elements, rotationDistance, stepCounter);
        print(elements);
        System.out.println(this.getClass().getSimpleName() + " : " + stepCounter);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    /* Alg 2: Juggling (dolphin) rotation */
    private void juggleRot(T[] elements, int rotationDistance, MutableInt stepCounter) {
        int cycles = Gcd.FUNCTION.applyAsInt(rotationDistance, elements.length);
        for (int i = 0; i < cycles; i++) {
            System.out.print("/");
            /* move i-th values of blocks */
            T t = elements[i];
            stepCounter.increment();
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
                stepCounter.increment();
                print(elements);
                j = k;
            }
            elements[j] = t;
            stepCounter.increment();
            print(elements);
        }
    }
}
