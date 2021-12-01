package com.codebreeze.algorithms.pearls;

import java.util.function.Consumer;

import static org.apache.commons.lang3.ArrayUtils.swap;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class QuickSort implements Consumer<int[]> {

    private final Algorithm algorithm;

    public QuickSort(Algorithm algorithm) {
        if (algorithm == null) {
            throw new NullPointerException("cannot work with null algorithm");
        }
        this.algorithm = algorithm;
    }

    @Override
    public void accept(int[] x) {
        if (x == null) {
            throw new NullPointerException("cannot sort null int arrays");
        }
        algorithm.accept(x);
    }

    enum Algorithm implements Consumer<int[]> {
        /*
procedure QSort(L,U)
    if L < U then
        Swap(X[L], X[RandInt(L,U)])
        T := X[L]
        M := L
        for I := L+1 to U do
            if X[I] < T then
                M := M+1
                Swap(X[M], X[I])
        Swap(X[L], X[M])
        QSort(L, M-1)
        QSort(M+1, U)
         */
        ALG1 {
            @Override
            public void accept(int[] x) {
                qsort(x, 0, x.length - 1);
            }

            private static void qsort(int[] x, int l, int u) {
                if (l < u) {
                    int random = nextInt(l, u + 1);
                    swap(x, l, random);
                    int t = x[l];
                    int m = l;
                    for (int i = l + 1; i <= u; i++) {
                        if (x[i] < t) {
                            m = m + 1;
                            swap(x, m, i);
                        }
                    }
                    swap(x, l, m);
                    qsort(x, l, m - 1);
                    qsort(x, m + 1, u);
                }
            }
        },
        /*
        procedure QSort(L,U)
            if L < U then
                Swap(X[L], X[RandInt(L,U)])
                I := L
                J := U + 1
                T := X[L]
                loop
                    repeat I := I+1 until X[I] >= T
                    repeat J := J-1 until X[J] <= T
                    if J < I then
                        break
                    Swap(X[I],X[J])
                Swap(X[L],X[J])
                QSort(L,J-1)
                QSort(I,U)
         */
        ALG2 {
            @Override
            public void accept(int[] x) {
                qsort(x, 0, x.length - 1);
            }

            private static void qsort(int[] x, int l, int u) {
                if (l < u) {
                    int random = nextInt(l, u + 1);
                    swap(x, l, random);
                    int i = l;
                    int j = u + 1;
                    int t = x[l];
                    while (true) {
                        do {
                            i = i + 1;
                        }
                        while (i < x.length && x[i] < t);

                        do {
                            j = j - 1;
                        } while (j > 0 && x[j] > t);

                        if (j < i) {
                            break;
                        }

                        swap(x, i, j);
                    }
                    swap(x, l, j);
                    qsort(x, l, j - 1);
                    qsort(x, i, u);
                }
            }
        }
    }
}
