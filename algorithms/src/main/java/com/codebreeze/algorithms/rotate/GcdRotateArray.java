package com.codebreeze.algorithms.rotate;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class GcdRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        MutableInt stepCounter = new MutableInt();
        print(elements);
        gcdRotate(elements, rotationDistance, stepCounter);
        print(elements);
        System.out.println(this.getClass().getSimpleName() + " : " + stepCounter);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    /* Alg 3: Recursive rotate (using gcd structure) */
    // this swaps the region from i to i+k-1 with the region from j to j+k-1
    private void swap(T[] elements, int i, int j, int k, MutableInt stepCounter) /* swap x[i..i+k-1] with x[j..j+k-1] */ {
        while (k-- > 0) {
            System.out.print('|');
            T t = elements[i];
            elements[i] = elements[j];
            print(elements);
            elements[j] = t;
            stepCounter.increment();
            print(elements);
            i++;
            j++;
        }
    }

    private void gcdRotate(T[] elements, int rotationDistance, MutableInt stepCounter) {
        int i, j, p;
        if (rotationDistance == 0 || rotationDistance == elements.length) {
            return;
        }
        i = p = rotationDistance;
        j = elements.length - p;
        while (i != j) {
            System.out.print("/");
/* invariant:
 x[0 ..p-i ] is in final position
 x[p-i..p-1 ] = a (to be swapped with b)
 x[p ..p+j-1] = b (to be swapped with a)
 x[p+j..n-1 ] in final position
 */
            // not here the larger and not larger is related to the gcd laws
            if (i > j) {
                swap(elements, p - i, p, j, stepCounter);
                i -= j;
            } else {
                swap(elements, p - i, p + j - i, i, stepCounter);
                j -= i;
            }
        }
        swap(elements, p - i, p, i, stepCounter);
    }
}
