package com.codebreeze.algorithms.pearls;

import java.util.Objects;
import java.util.function.Consumer;

import static org.apache.commons.lang3.ArrayUtils.swap;

public class HeapSort2 implements Consumer<int[]> {

    /*
for I := 2 to N do
    SiftUp(I)
for I := N downto 2 do
    Swap(X[1], X[I])
    SiftDown(I-1)
 */
    @Override
    public void accept(int[] x) {
        Objects.requireNonNull(x, "cannot operate on a null array");
        for (int i = 1; i < x.length; i++) {
            siftUp(x, i);
        }
        for (int i = x.length - 1; i > 0; i--) {
            swap(x, 0, i);
            siftDown(x, i - 1);
        }
    }

    /*
    procedure SiftUp(N)
            pre Heap(1, N-1) and N > 0
            post Heap(1, N)
        I := N
        loop
            \* Invariant: Heap(1,N) except perhaps
                between I and its parent *\/
            if I = 1 then break
            P := I div 2
            if X[P] <= X[I] then break
            Swap(X[P], X[I])
            I := P
     */
    private static void siftUp(int[] x, int n) {
        int i = n;
        while (true) {
            if (i == 0) {
                break;
            }
            int p = parentIndex(i);
            if (x[p] >= x[i]) { // modified < to >
                break;
            }
            swap(x, p, i);
            i = p;
        }
    }

    /*
procedure SiftDown(N)
        pre Heap(2, N) and N >= 0
        post Heap(1, N)
    I := 1
    loop
        /* Invariant: Heap(1,N) except perhaps between I and its (0, 1 or 2) children *\/
        C := 2*I
        if C > N then break
        /* C is the left child of I *\/
        if C+1 <= N then
        /* C+1 is the right child of I *\/
            if X[C+1] < X[C] then
                C := C+1
        /* C is the least child of I *  /
        if X[I] <= X[C] then break
        Swap(X[C], X[I])
        I := C
     */
    private static void siftDown(int[] x, int n) {
        int i = 0;
        while (true) {
            int c = leftChildIndex(i);
            if (c > n) {
                break;
            }
            int r = rightChildIndex(i);
            if (r <= n) {
                if (x[r] > x[c]) { // modified < to >
                    c = r;
                }
            }
            if (x[i] >= x[c]) { // modified < to >
                break;
            }
            swap(x, c, i);
            i = c;
        }
    }

    private static int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private static int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private static int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }
}
