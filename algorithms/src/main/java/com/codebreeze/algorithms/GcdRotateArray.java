package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class GcdRotateArray<T> implements BiConsumer<T[], Integer> {

    @Override
    public void accept(T[] elements, Integer I) {
        print(elements);
        gcdRotate(elements, I);
        print(elements);
    }

    private void print(T[] ts) {
        System.out.println(Arrays.toString(ts));
    }

    private void print(T t) {
        System.out.println(t);
    }

    /* Alg 3: Recursive rotate (using gcd structure) */
    private void swap(T[] elements, int i, int j, int k) /* swap x[i..i+k-1] with x[j..j+k-1] */ {
        while (k-- > 0) {
            T t = elements[i];
            elements[i] = elements[j];
            elements[j] = t;
            i++;
            j++;
        }
    }

    private void gcdRotate(T[] elements, int rotdist) {
        int i, j, p;
        if (rotdist == 0 || rotdist == elements.length)
            return;
        i = p = rotdist;
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
