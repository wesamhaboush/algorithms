package com.codebreeze.algorithms.pearls;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class BinarySearch1000 implements IntUnaryOperator {
    private final int[] values;
    private final Algorithm algorithm;

    public BinarySearch1000(int[] values, Algorithm algorithm) {
        if (values == null || algorithm == null) {
            throw new NullPointerException(
                String.format(
                    "neither values nor algorithm can be null. algorithm=%s, values=%s",
                    algorithm,
                    Arrays.toString(values)
                )
            );
        }
        failIfNot1000(values);
        this.values = values;
        this.algorithm = algorithm;
    }

    @Override
    public int applyAsInt(int targetValue) {
        return algorithm.apply(this.values, targetValue);
    }

    enum Algorithm {
        A {
            @Override
            public int apply(int[] x, int t) {
                /*
                L := 1
                U := N
                loop
                    // Invariant : if T is in X, it is in X[L..U]
                    if L > U then
                        P := 0
                        break
                    M := (L+U) div 2
                    case
                        X[M] < T:
                            L := M + 1
                        X[M] = T:
                            P := M
                            break
                        X[M] > T:
                            U := M-1
                 */
                int l = 0;
                int u = x.length - 1;
                while (true) {
                    if (l > u) {
                        return -1;
                    }
                    int m = (l + u) / 2;
                    if (x[m] < t) {
                        l = m + 1;
                    }
                    if (x[m] == t) {
                        return m;
                    }
                    if (x[m] > t) {
                        u = m - 1;
                    }
                }
            }
        },
        B {
            @Override
            public int apply(int[] x, int t) {
                /*
                L := 0
                U := N + 1
                while L + 1 != U do
                    // Invariant: X[L] < T and X[U] >= T and L < U
                    M := (L+U) div 2
                    if X[M] < T then
                        L := M
                    else
                        U := M
                P := U
                if P > N or X[P] != T then P := 0
                 */
                int l = -1;
                int u = x.length;
                while (l + 1 != u) {
                    int m = (l + u) / 2;
                    if (x[m] < t) {
                        l = m;
                    } else {
                        u = m;
                    }
                }
                if (u > x.length - 1 || x[u] != t) {
                    return -1;
                } else {
                    return u;
                }
            }
        },
        C {
            @Override
            public int apply(int[] x, int t) {
                /*
                I := 512
                if X[512] >= T then
                    L := 0
                else
                    L := 1000 + 1 - 512
                while I != 1 do
                    // Invariant: X[L] < T and X[L + I] >= T and I = 2**j
                    NextI := I div 2
                    if X[L+NextI] < T then
                        L := L + NextI
                        I := NextI
                    else
                        I := NextI
                 P := L+1
                 if P > 1000 or X[P] != T then
                    P := 0
                 */
                int i = 512;
                int l = x[512 - 1] >= t
                    ? -1
                    : (1000 - 512);
                while (i != 1) {
                    int nextI = i / 2;
                    if (x[l + nextI] < t) {
                        l = l + nextI;
                        i = nextI;
                    } else {
                        i = nextI;
                    }
                }
                int p = l + 1;
                if (p > (1000 - 1) || x[p] != t) {
                    return -1;
                } else {
                    return p;
                }
            }
        },
        D {
            @Override
            public int apply(int[] x, int t) {
                /*
                I := 512
                L := 0
                if X[512] < T then
                    L := 1000 + 1 - 512
                while I != 1 do
                    // Invariant: X[L] < T and X[L+1] >= T and I = 2**J
                    I := I div 2
                    if X[L+1] < T then
                        L := L+I
                P := L+1
                if P > 1000 or X[P] != T then
                    P := 0
                 */
                int i = 512;
                int l = -1;
                if (x[512 - 1] < t) {
                    l = 1000 - 512;
                }
                while (i != 1) {
                    i = i / 2;
                    if (x[l + i] < t) {
                        l = l + i;
                    }
                }
                int p = l + 1;
                if (p > (1000 - 1) || x[p] != t) {
                    return -1;
                } else {
                    return p;
                }
            }
        },
        E {
            @Override
            public int apply(int[] x, int t) {
                /*
                L := 0
                if X[512] < T then
                    L := 1000 + 1 - 512
                if X[L + 256 ] < T then
                    L := L + 256
                if X[L + 128] < T then
                    L := L + 128
                if X[L + 64] < T then
                    L := L + 64
                if X[L + 32] < T then
                    L := L + 32
                if X[L + 16] < T then
                    L := L + 16
                if X[L + 8] < T then
                    L := L + 8
                if X[L + 4] < T then
                    L := L + 4
                if X[L + 2] < T then
                    L := L + 2
                if X[L + 1] < T then
                    L := L + 1
                P := L + 1
                if P > 1000 or X[P] != T then P := 0
                 */
                int l = -1;
                if (x[512 - 1] < t) {
                    l = 1000 - 512;
                }
                if (x[l + 256] < t) {
                    l = l + 256;
                }
                if (x[l + 128] < t) {
                    l = l + 128;
                }
                if (x[l + 64] < t) {
                    l = l + 64;
                }
                if (x[l + 32] < t) {
                    l = l + 32;
                }
                if (x[l + 16] < t) {
                    l = l + 16;
                }
                if (x[l + 8] < t) {
                    l = l + 8;
                }
                if (x[l + 4] < t) {
                    l = l + 4;
                }
                if (x[l + 2] < t) {
                    l = l + 2;
                }
                if (x[l + 1] < t) {
                    l = l + 1;
                }
                int p = l + 1;
                if (p > (1000 - 1) || x[p] != t) {
                    return -1;
                } else {
                    return p;
                }
            }
        };

        public abstract int apply(int[] x, int t);
    }

    private static void failIfNot1000(int[] x) {
        if (x.length != 1000) {
            throw new IllegalArgumentException(
                "cannot operate on arrays that do not have 1000 elements, input has element count: " + x.length
            );
        }
    }
}
