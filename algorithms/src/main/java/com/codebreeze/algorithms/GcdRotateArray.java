package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class GcdRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer rotationDistance) {
        print(elements);
        gcdRotate(elements, rotationDistance);
        print(elements);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    /* Alg 3: Recursive rotate (using gcd structure) */
    private void swap(T[] elements, int i, int j, int k) /* swap x[i..i+k-1] with x[j..j+k-1] */ {
        while (k-- > 0) {
            System.out.print('|');
            T t = elements[i];
            elements[i] = elements[j];
            elements[j] = t;
            i++;
            j++;
        }
    }

    private void gcdRotate(T[] elements, int rotationDistance) {
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
            if (i > j) {
                swap(elements, p - i, p, j);
                i -= j;
            } else {
                swap(elements, p - i, p + j - i, i);
                j -= i;
            }
        }
        swap(elements, p - i, p, i);
    }
}
