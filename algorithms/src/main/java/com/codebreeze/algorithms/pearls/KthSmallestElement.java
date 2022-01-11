package com.codebreeze.algorithms.pearls;

import java.util.function.ToIntFunction;

import static com.codebreeze.algorithms.pearls.KthSmallestElement.KthSmallestSpecs;
import static org.apache.commons.lang3.ArrayUtils.swap;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class KthSmallestElement implements ToIntFunction<KthSmallestSpecs> {

    private final Algorithm algorithm;

    public KthSmallestElement(Algorithm algorithm) {
        if (algorithm == null) {
            throw new NullPointerException("cannot operate on null algorithms");
        }
        this.algorithm = algorithm;
    }

    @Override
    public int applyAsInt(KthSmallestSpecs specs) {
        return algorithm.applyAsInt(specs);
    }

    enum Algorithm implements ToIntFunction<KthSmallestSpecs> {
        /*
procedure Select(L, U, K)
    pre L <= K <= U
    post X[L..K-1] <= X[K] <= X[K+1..U]
    if L < U then
        Swap(X[L], X[RandInt(L, U)])
        T := X[L]
        M := L
        for I := L+1 to U do
            \/* Invariant: X[L+1..M] < T
                and X[M+1..I-1] >= T *\/
            if X[I] < T then
                M := M+1
                Swap(X[M], X[I])
        Swap(X[L], X[M])
        \/* X[L..M-1] <= X[M] <= X[M+1..U] *\/
        if M < K then Select(M+1, U, K)
        else if M > K then Select(L, M-1, K)
         */
        RECURSION {
            @Override
            public int applyAsInt(KthSmallestSpecs specs) {
                return select(specs.elements, 0, specs.elements().length - 1, specs.k());
            }

            private int select(int[] x, int l, int u, int k) {
                if (l == u) {
                    return x[l]; // or return u, they are equal!
                } else if (l < u) {
                    swap(x, l, nextInt(l, u + 1));
                    int t = x[l];
                    int m = l;
                    for (int i = l + 1; i <= u; i++) {
                        if (x[i] < t) {
                            m = m + 1;
                            swap(x, m, i);
                        }
                    }
                    swap(x, l, m);
                    if (m < k) {
                        return select(x, m + 1, u, k);
                    } else if (m > k) {
                        return select(x, l, m - 1, k);
                    } else {
                        return x[k];
                    }
                } else {
                    throw new IllegalArgumentException("something gone wrong! why are we here!");
                }
            }
        },
        /*
        procedure Select(K)
            post X[1..K-1] <= X[K] <= X[K+1..N]
            L := 1
            U := N
            while L < U do
                \/* Invariant: X[1..L-1] <= X[L..U] <= X[U+1..N] *\/
                    ... loop body here
                \/* Invariant and X[L..M-1] <= X[M] <= X[M+1..U] *\/
                if M <= K then L := M+1
                if M >= K then U := M-1
        */
        LOOP {
            @Override
            public int applyAsInt(KthSmallestSpecs specs) {
                int[] x = specs.elements();
                int l = 0;
                int u = x.length - 1;
                while (l < u) {
                    swap(x, l, nextInt(l, u + 1));
                    int t = x[l];
                    int m = l;
                    for (int i = l + 1; i <= u; i++) {
                        if (x[i] < t) {
                            m = m + 1;
                            swap(x, m, i);
                        }
                    }
                    swap(x, l, m);
                    if (m <= specs.k()) {
                        l = m + 1;
                    } else { // m >= specs.k()
                        u = m - 1;
                    }
                }
                return x[specs.k()];
            }
        }
    }

    static record KthSmallestSpecs(int[] elements, int k) {
        public KthSmallestSpecs {
            if (elements == null) {
                throw new NullPointerException("cannot operate on null arrays");
            }
            if(elements.length == 0) {
                throw new IllegalArgumentException(
                    "there are not values of k that can be obtained within an empty array"
                );
            }
            if (k < 0 || k > elements.length - 1) {
                throw new IllegalArgumentException(
                    "cannot have k's greater than " + (elements.length - 1) + " or less than 0, but was " + k
                );
            }
        }
    }
}
