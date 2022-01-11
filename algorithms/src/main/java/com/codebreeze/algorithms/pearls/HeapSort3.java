package com.codebreeze.algorithms.pearls;

import java.util.Objects;
import java.util.function.Consumer;

import static org.apache.commons.lang3.ArrayUtils.swap;

public class HeapSort3 implements Consumer<int[]> {

    /*
for I := int(N/2) downto 1 do
    SiftDown(I, N)
for I := N downto 2 do
    Swap(X[1], X[I])
    SiftDown(1, I-1)
 */
    @Override
    public void accept(int[] x) {
        Objects.requireNonNull(x, "cannot operate on a null array");
        for (int i = ((x.length - 1) / 2); i >= 0; i--) {
            siftDown(x, i, x.length - 1);
        }
        for (int i = x.length - 1; i > 0; i--) {
            swap(x, 0, i);
            siftDown(x, 0, i - 1);
        }
    }

    /*
procedure SiftDown(L, U)
        pre MaxHeap(L+1, U)
        post MaxHeap(L, U)
    I := L
    loop
        /* Invariant: MaxHeap(L,U) except perhaps between I and its (0, 1, or 2) children *\/
        C := 2*I
        if C > U then break
        if C + 1 <= U and X[C+1] > X[C] then C := C+1
        /* C is the greatest child of I *\/
        if X[I] >= X[C] then break
        Swap(X[C], X[I])
        I := C
     */
    private static void siftDown(int[] x, int l, int u) {
        int i = l;
        while (true) {
            int c = leftChildIndex(i);
            if (c > u) {
                break;
            }
            int r = rightChildIndex(i);
            if (r <= u) {
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

    private static int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private static int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }
}
