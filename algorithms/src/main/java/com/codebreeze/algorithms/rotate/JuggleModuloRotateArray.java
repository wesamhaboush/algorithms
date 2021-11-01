package com.codebreeze.algorithms.rotate;

import com.codebreeze.algorithms.Gcd;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class JuggleModuloRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        MutableInt stepCounter = new MutableInt();
        print(elements);
        juggleRotModulo(elements, rotationDistance, stepCounter);
        print(elements);
        System.out.println(this.getClass().getSimpleName() + " : " + stepCounter);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    private void juggleRotModulo(T[] elements, int rotationDistance, MutableInt stepCounter) {
        int cycles = Gcd.FUNCTION.applyAsInt(rotationDistance, elements.length);
        for (int i = 0; i < cycles; i++) {
            System.out.print("/");
            /* move i-th values of blocks */
            T t = elements[i];
            stepCounter.increment();
            int j = i;
            for (; ; ) {
                System.out.print("|");
/* Replace with mod below
 k = j + rotationDistance;
 if (k >= n)
 k -= n;
 */
                int k = (j + rotationDistance) % elements.length;
                // if this happens that means we have moved all the vector for that cycle
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
