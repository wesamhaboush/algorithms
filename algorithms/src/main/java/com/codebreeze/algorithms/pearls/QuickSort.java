package com.codebreeze.algorithms.pearls;

import java.util.Objects;
import java.util.function.Consumer;

import static org.apache.commons.lang3.ArrayUtils.swap;
import static org.apache.commons.lang3.RandomUtils.nextInt;

/*
Note: most algorithms differ in how they do the partitioning to do quick sort.
 */
public class QuickSort implements Consumer<int[]> {

    private final Algorithm algorithm;

    public QuickSort(Algorithm algorithm) {
        this.algorithm = Objects.requireNonNull(algorithm, "cannot work with null algorithm");
    }

    @Override
    public void accept(int[] x) {
        Objects.requireNonNull(x, "cannot sort null in arrays");
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
        },
        /*
procedure QSort(L,U)
    if L < U then
        Swap(X[L], X[RandInt(L,U)])
        T := X[L]
        M := U+1
        for I := U downto L do
            if X[I] >= T then
                M := M-1
                Swap(X[M], X[I])
        QSort(L, M-1)
        QSort(M+1, U)
         */
        ALG3 {
            @Override
            public void accept(int[] x) {
                qsort(x, 0, x.length - 1);
            }

            private static void qsort(int[] x, int l, int u) {
                if (l < u) {
                    int random = nextInt(l, u + 1);
                    swap(x, l, random);
                    int t = x[l];
                    int m = u + 1;
                    for (int i = u; i >= l; i--) {
                        if (x[i] >= t) {
                            m = m - 1;
                            swap(x, m, i);
                        }
                    }
                    qsort(x, l, m - 1);
                    qsort(x, m + 1, u);
                }
            }
        },
        /*
procedure QSort(L,U)
    if L < U then
        Swap(X[L], X[RandInt(L,U)])
        T := X[L]
        M := U+1
        I := U+1
        repeat
                repeat I := I-1 until X[I] >= T
                M := M-1
                Swap(X[M], X[I])
            until I = L
        QSort(L, M-1)
        QSort(M+1, U)
         */
        ALG4 {
            @Override
            public void accept(int[] x) {
                qsort(x, 0, x.length - 1);
            }

            private static void qsort(int[] x, int l, int u) {
                if (l < u) {
                    int random = nextInt(l, u + 1);
                    swap(x, l, random);
                    int t = x[l];
                    int m = u + 1;
                    int i = u + 1;
                    do {
                        do {
                            i = i - 1;
                        } while (x[i] < t);
                        m = m - 1;
                        swap(x, m, i);
                    } while (i != l);
                    qsort(x, l, m - 1);
                    qsort(x, m + 1, u);
                }
            }
        },
        ALG5 {
            @Override
            public void accept(int[] x) {
                qsort(x, 0, x.length - 1);
            }

            static int partition(int[] x, int l, int u) {
                int pivot = x[u];

                // Index of smaller element and
                // indicates the right position
                // of pivot found so far
                int i = l - 1;

                for (int j = l; j <= u - 1; j++) {
                    if (x[j] < pivot) {
                        // Increment index of
                        // smaller element
                        i++;
                        swap(x, i, j);
                    }
                }
                swap(x, i + 1, u);
                return i + 1;
            }

            private static void qsort(int[] x, int l, int u) {
                while (l < u) {
        /* m is partitioning index, x[p] is now
           at right place */
                    int m = partition(x, l, u);

                    // If left part is smaller, then recur for left
                    // part and handle right part iteratively
                    if (m - l < u - m) {
                        qsort(x, l, m - 1);
                        l = m + 1;
                    }
                    // Else recur for right part
                    else {
                        qsort(x, m + 1, u);
                        u = m - 1;
                    }
                }
            }
        }
    }
}
